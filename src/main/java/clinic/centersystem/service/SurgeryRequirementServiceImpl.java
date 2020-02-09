package clinic.centersystem.service;

import clinic.centersystem.converter.SurgeryRequirementConverter;
import clinic.centersystem.dto.request.SurgeryReservationReqDTO;
import clinic.centersystem.dto.response.RoomResponseDTO;
import clinic.centersystem.dto.response.SurgeryRequirementResponseDTO;
import clinic.centersystem.exception.ResourceNotExistsException;
import clinic.centersystem.model.*;
import clinic.centersystem.repository.SurgeryRequirementRepository;
import clinic.centersystem.service.intf.*;
import org.joda.time.DateTime;
import org.joda.time.DateTimeZone;
import org.joda.time.LocalDate;
import org.joda.time.format.DateTimeFormat;
import org.joda.time.format.DateTimeFormatter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.orm.ObjectOptimisticLockingFailureException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class SurgeryRequirementServiceImpl implements SurgeryRequirementService {

    @Autowired
    private SurgeryRequirementRepository surgeryRequirementRepository;

    @Autowired
    private RoomService roomService;

    @Autowired
    private RoomCalendarService roomCalendarService;

    @Autowired
    private DoctorService doctorService;

    @Autowired
    private CalendarService calendarService;

    @Autowired
    private CalendarItemService calendarItemService;

    @Autowired
    private PatientService patientService;

    @Autowired
    private SurgeryService surgeryService;

    @Autowired
    private EmailService emailService;


    @Override
    public SurgeryRequirement findById(Long id) {
        return surgeryRequirementRepository.findById(id).orElseThrow(() -> new ResourceNotExistsException("Surgery requirement doesn't exist"));
    }

    @Override
    public List<SurgeryRequirement> findAll() {
        return surgeryRequirementRepository.findAll();
    }


    @Override
    public SurgeryRequirementResponseDTO findByClinicId(Long clinicId, Integer pageCnt) {
        Pageable pageable = PageRequest.of(pageCnt, 10);
        Page<SurgeryRequirement> surgeryRequirements = surgeryRequirementRepository.findByClinicId(clinicId, pageable);
        SurgeryRequirementResponseDTO surgeryRequirementResponseDTO = new SurgeryRequirementResponseDTO();

        surgeryRequirementResponseDTO.setSurgeryRequirements(surgeryRequirements.getContent().stream().map(SurgeryRequirementConverter::fromSurReqToSurReqDTO).collect(Collectors.toList()));
        surgeryRequirementResponseDTO.setPageCount(surgeryRequirements.getTotalPages());

        return surgeryRequirementResponseDTO;
    }

    @Override
    @Transactional(propagation = Propagation.REQUIRES_NEW)
    public int reserveRoomForSurgery(SurgeryReservationReqDTO surgeryReservationReqDTO) {
        this.findById(surgeryReservationReqDTO.getPickedSurReq().getId());


        String pickedDateStr = surgeryReservationReqDTO.getPickedTerm().split(" ")[0];
        String pickedTermsStr[] = (surgeryReservationReqDTO.getPickedTerm().split(" ")[1]).split("-");
        Integer pickedTermStart = Integer.valueOf(pickedTermsStr[0]);
        Integer pickedTermEnd = Integer.valueOf(pickedTermsStr[1]);
        DateTime pickedDate = new DateTime(pickedDateStr, DateTimeZone.UTC);

        DateTime pickedDateStart = new DateTime(pickedDateStr, DateTimeZone.UTC);
        pickedDateStart = pickedDateStart.plusHours(pickedTermStart);

        DateTime pickedDateEnd = new DateTime(pickedDateStr, DateTimeZone.UTC);
        pickedDateEnd = pickedDateEnd.plusHours(pickedTermEnd);

        Room roomCheck = roomService.findById(surgeryReservationReqDTO.getPickedRoom());
        if (!roomCheck.getType().equals("SUR")) {
            return 4;
        }

        List<Integer> bookedTerm = roomCalendarService.findByRoomAndDate(surgeryReservationReqDTO.getPickedRoom(), pickedDate);

        if (bookedTerm.contains(pickedTermStart)) {
            return 1;
        }

        surgeryReservationReqDTO.getChosenDoc().add(surgeryReservationReqDTO.getPickedSurReq().getDoctorId());
        boolean avDoctors = false;
        Doctor doctor;
        Long calendarId;
        Integer cntCi;
        Surgery surgery = null;
        Patient patient = null;
        Room room = null;
        List<Doctor> avalDoctors = new ArrayList<>();
        for (Long docId : surgeryReservationReqDTO.getChosenDoc()) {
            doctor = doctorService.findOneById(Long.valueOf(docId));

//            otkomentarisati ako se zeli isprobati lock baze
//            try {
//                Thread.sleep(7000);
//            } catch (InterruptedException e) {
//            }



            if (!(doctor.getStartTime() <= pickedTermStart && doctor.getEndTime() >= pickedTermEnd)) {
                //doktor ne moze da prisustvuje operaciji jer operacija nije u sklopu radnog vremena
                continue;
            }

            avalDoctors.add(doctor);
            calendarId = calendarService.findCalendarIdByPersonnelId(doctor.getId());
            cntCi = calendarItemService.findByCalendarIdandDate(calendarId, pickedDateStart, pickedDateEnd);
            if (cntCi == 0) {
                avDoctors = true;
                //doktor ima slobodnih termina unjeti mu u kalendar sta treba
                if (surgery == null) {
                    patient = patientService.findOneById(surgeryReservationReqDTO.getPickedSurReq().getPatientId());
                    room = roomService.findOneById(surgeryReservationReqDTO.getPickedRoom());
                    RoomCalendar roomCalendar = RoomCalendar.builder()
                            .date(pickedDate)
                            .room(room)
                            .termin(pickedTermStart)
                            .build();
                    roomCalendarService.save(roomCalendar);
                    surgery = Surgery.builder()
                            .startTime(pickedDateStart)
                            .endTime(pickedDateEnd)
                            .room(room)
                            .patient(patient)
                            .build();
                    surgeryService.save(surgery);
                }
                Calendar calendar = calendarService.findOneById(calendarId);
                CalendarItem calendarItem = CalendarItem.builder()
                        .start(pickedDateStart)
                        .end(pickedDateEnd)
                        .allDay("N")
                        .title("Surgery")
                        .type("SUR")
                        .calendar(calendar)
                        .typeId(surgery.getId())
                        .build();
                calendarItemService.save(calendarItem);
                doctorService.save(doctor);
            }
        }

        roomService.save(room);

        if (!avDoctors) {
            return 2;
        }

        this.deleteById(surgeryReservationReqDTO.getPickedSurReq().getId());


        for (Doctor doc : avalDoctors) {
            String subject = "Term for surgery";
            String answer = "Term of surgery for patient " + patient.getFirstName() + " " + patient.getLastName() + " is\n" +
                    surgeryReservationReqDTO.getPickedTerm() + "\n" +
                    "Room for surgery is: " + room.getName() + " " + room.getRoomNum();

            emailService.sendMailTo(doc.getEmail(), subject, answer);
        }


        if (!pickedDateStr.equals(surgeryReservationReqDTO.getPickedSurReq().getDate()) || !pickedTermsStr[0].equals(surgeryReservationReqDTO.getPickedSurReq().getTermin().toString())) {
            String subject = "Term for surgery";
            String answer = "Your term for surgery is changed from:\n" +
                    surgeryReservationReqDTO.getPickedSurReq().getDate() + " " + pickedTermsStr[0] + "-" + (Integer.valueOf(pickedTermsStr[0]) + 3) + " to " +
                    surgeryReservationReqDTO.getPickedTerm() + "\n" +
                    "Room for surgery is: " + room.getName() + " " + room.getRoomNum();

            emailService.sendMailTo(patient.getEmail(), subject, answer);
        } else {
            String subject = "Term for surgery";
            String answer = "Your term for surgery is\n" +
                    surgeryReservationReqDTO.getPickedTerm() + "\n" +
                    "Room for surgery is: " + room.getName() + " " + room.getRoomNum();

            emailService.sendMailTo(patient.getEmail(), subject, answer);
        }
        return 3;


    }

    @Override
    @Transactional(propagation = Propagation.REQUIRED)
    public void deleteById(Long id) {
        surgeryRequirementRepository.deleteById(id);
        return;
    }

    @Override
    public void autoReserveRoomForSurgery() {
        List<SurgeryRequirement> surgeryRequirements = surgeryRequirementRepository.findAll();
        for (SurgeryRequirement surgeryRequirement : surgeryRequirements) {
            DateTime pickedDate = surgeryRequirement.getDate();

            List<Room> rooms = roomService.findByClinicIdAndType(surgeryRequirement.getClinic().getId(), "SUR");
            for (Room room : rooms) {
                DateTime dt = new DateTime(pickedDate, DateTimeZone.UTC);
                DateTime now = new DateTime(LocalDate.now().toString(), DateTimeZone.UTC);
                if (dt.isBefore(now) || dt.equals(now)) {
                    dt = now.plusDays(1);
                }
                boolean flag = true;
                boolean roomRes = false;
                Integer firstFree = 7;
                while (flag) {
                    List<Integer> termins = roomCalendarService.findByRoomAndDate(room.getId(), dt);
                    for (int i = 7; i <= 16; i += 3) {
                        if (!termins.contains(i)) {
                            firstFree = i;
                            flag = false;
                            break;
                        }
                    }
                    if (flag) {
                        dt = dt.plusDays(1);
                        continue;
                    }

                    Integer pickedTermStart = firstFree;
                    Integer pickedTermEnd = firstFree + 3;
                    DateTimeFormatter dtf = DateTimeFormat.forPattern("yyyy-MM-dd");

                    SurgeryReservationReqDTO surgeryReservationReqDTO = SurgeryReservationReqDTO.builder()
                            .pickedRoom(room.getId())
                            .pickedTerm(dtf.print(dt) + " " + pickedTermStart + "-" + pickedTermEnd)
                            .chosenDoc(new ArrayList<Long>(Arrays.asList(surgeryRequirement.getDoctorId())))
                            .pickedSurReq(SurgeryRequirementConverter.fromSurReqToSurReqDTO(surgeryRequirement))
                            .build();
                    int ans = this.reserveRoomForSurgery(surgeryReservationReqDTO);

                    if (ans == 3) {
                        roomRes = true;
                    } else {
                        flag = true;
                        dt = dt.plusDays(1);
                    }
                }

                if (roomRes) {
                    break;
                }
            }
        }

    }

    @Override
    public void delete(SurgeryRequirement surgeryRequirement) {
        surgeryRequirementRepository.delete(surgeryRequirement);
        return;
    }


}
