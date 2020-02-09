import React from 'react';
import { Route, Redirect, Switch } from 'react-router-dom';
import HomePage from './pages/HomePage';
import LoginPage from './pages/LoginPage';
import PatientHomePage from './pages/PatientHomePage';
import RegPage from './pages/RegPage';
import DoctorHomePage from './pages/DoctorHomePage';
import NurseHomePage from './pages/NurseHomePage';
import ClinicCenterAdminProfile from './pages/ClinicCenterAdminProfile';
import HolidayAproval from './components/HolidayAproval';
import PasswordChanger from './components/PasswordChanger';
import ClinicAdmin from './components/ClinicAdmin/CA';
import RoomAllAtOnce from './components/Room/RoomAllAtOnce';
import RoomSearch from './components/Room/RoomSearch';
import PrivateRoute from './components/PrivateRoute';
import DoctorAllAtOnce from './components/ClinicAdmin/DoctorAllAtOnce';
import ClinicProfile from './components/ClinicAdmin/ClinicProfile';
import BusinessReport from './components/ClinicAdmin/BusinessReport';
import UserProfile from './components/UserProfile';
import FreeAppointment from './components/ClinicAdmin/FreeApointment';
import AppointmentTypAllAtOnce from './components/ClinicAdmin/AppointmentTypeAllAtOnce';
import Pricelist from './components/ClinicAdmin/Pricelist';
import SurgeryReq from './components/SurgeryRequirement/SurgeryReq';
import Unauthorized from './components/Unauthorized';
import PatientPagination from './components/Patient/PatientPagination';
import WorkCalendar from './components/Calendar/WorkCalendar';
import HolAbsRequest from './components/HolidayAbsence/HolAbsRequest';
import BookingDoc from './components/BookingDoc';
import AppointmentInfo from './components/ApointmentInfo';
import SurReqDoctors from './components/SurgeryRequirement/SurReqDoctors';
import AddDiagnose from './components/MedicineDiagnose/AddDiagnose';
import AddMedicine from './components/MedicineDiagnose/AddMedicine';
import RegistrationAproval from './components/RegistrationReq/RegistrationAproval';
import ClinicReg from './components/Clinic/ClinicReg';
import AdminReg from './components/ClinicCenterAdmin/AdminReg';
import ClinicAdminReg from './components/ClinicAdmin/ClinicAdminReg';
import MedicalRecord from './components/MedicalRecord/MedicalRecord';
import ClinicSearch from './components/Clinic/ClinicSearch';
import DoctorSearch from './components/Doctor/DoctorSearch';
import History from './components/Patient/History';
import RewritePrescription from './components/Prescriptions/RewritePrescription';
import PatientList from './components/Patient/PatientList';
import DoctorMedicalReport from './components/MedicalReport/DoctorMedicalReport';

const AppRouter = () => {
    return (
        <Switch>
            <Route exact path="/" component={HomePage} />
            <Route exact path="/login" component={LoginPage} />
            <Route exact path="/signup" component={RegPage} />
            <Route exact path="/prob" component={HolidayAproval} />
            <Route exact path="/signup" component={RegPage} />
            <Route exact path="/change-pass" component={PasswordChanger} />


            <PrivateRoute exact path="/nurse-page/rew-presc" component={RewritePrescription} accessRole={["ROLE_NURSE"]} />
            <PrivateRoute exact path="/nurse-page" component={NurseHomePage} accessRole={["ROLE_NURSE"]} />

            <PrivateRoute exact path="/medical-rec/:typeId" component={MedicalRecord} accessRole={["ROLE_DOCTOR","ROLE_PATIENT"]} />

            <PrivateRoute exact path="/ccadmin/regca" component={ClinicAdminReg} accessRole={["ROLE_CCADMIN"]} />
            <PrivateRoute exact path="/ccadmin/cli-reg" component={ClinicReg} accessRole={["ROLE_CCADMIN"]} />
            <PrivateRoute exact path="/ccadmin/reg-req" component={RegistrationAproval} accessRole={["ROLE_CCADMIN"]} />
            <PrivateRoute exact path="/ccadmin/add-med" component={AddMedicine} accessRole={["ROLE_CCADMIN"]} />
            <PrivateRoute exact path="/ccadmin/add-diag" component={AddDiagnose} accessRole={["ROLE_CCADMIN"]} />
            <PrivateRoute exact path="/ccadmin/cca-reg" component={AdminReg} accessRole={["ROLE_CCADMIN"]} />
            <PrivateRoute exact path="/ccadmin" component={ClinicCenterAdminProfile} accessRole={["ROLE_CCADMIN"]} />

            <PrivateRoute exact path="/pat/search-cli" component={ClinicSearch} accessRole={["ROLE_PATIENT"]} />
            <PrivateRoute exact path="/pat/search-doc" component={DoctorSearch} accessRole={["ROLE_PATIENT"]} />
            <PrivateRoute exact path="/pat/history" component={History} accessRole={["ROLE_PATIENT"]} />
            <PrivateRoute exact path="/pat" component={PatientHomePage} accessRole={["ROLE_PATIENT"]} />

            <PrivateRoute exact path="/doc/med-reps" component={DoctorMedicalReport} accessRole={["ROLE_DOCTOR"]} />
            <PrivateRoute exact path="/doc/pat-list" component={PatientList} accessRole={["ROLE_DOCTOR"]} />
            <PrivateRoute exact path="/doc/app-info" component={AppointmentInfo} accessRole={["ROLE_DOCTOR"]} />
            <PrivateRoute exact path="/doc/bok-doc" component={BookingDoc} accessRole={["ROLE_DOCTOR"]} />
            <PrivateRoute exact path="/doc" component={DoctorHomePage} accessRole={["ROLE_DOCTOR"]} />

            <PrivateRoute exact path="/pers/pat-list" component={PatientPagination} accessRole={["ROLE_NURSE", "ROLE_DOCTOR"]} />
            <PrivateRoute exact path="/pers/hol-abs" component={HolAbsRequest} accessRole={["ROLE_NURSE", "ROLE_DOCTOR"]} />
            <PrivateRoute exact path="/pers/work-cal" component={WorkCalendar} accessRole={["ROLE_NURSE", "ROLE_DOCTOR"]} />

            <PrivateRoute exact path="/adminc/pick-doc" component={SurReqDoctors} accessRole={["ROLE_ADMINC"]} />
            <PrivateRoute exact path="/adminc/room-search/:reason" component={RoomSearch} accessRole={["ROLE_ADMINC"]} />
            <PrivateRoute exact path="/adminc/exe-room/:clinicId" component={RoomAllAtOnce} accessRole={["ROLE_ADMINC"]} />
            <PrivateRoute exact path="/adminc/doc/:clinicId" component={DoctorAllAtOnce} accessRole={["ROLE_ADMINC"]} />
            <PrivateRoute exact path="/adminc/cli-prof/:clinicId" component={ClinicProfile} accessRole={["ROLE_ADMINC"]} />
            <PrivateRoute exact path="/adminc/bus-rep/:clinicId" component={BusinessReport} accessRole={["ROLE_ADMINC"]} />
            <PrivateRoute exact path="/adminc/free-app/:clinicId" component={FreeAppointment} accessRole={["ROLE_ADMINC"]} />
            <PrivateRoute exact path="/adminc/price-list/:clinicId" component={Pricelist} accessRole={["ROLE_ADMINC"]} />
            <PrivateRoute exact path="/adminc/app-type" component={AppointmentTypAllAtOnce} accessRole={["ROLE_ADMINC"]} />
            <PrivateRoute exact path="/adminc/sur-req/:clinicId" component={SurgeryReq} accessRole={["ROLE_ADMINC"]} />
            <PrivateRoute exact path="/adminc" component={ClinicAdmin} accessRole={["ROLE_ADMINC"]} />

            <PrivateRoute exact path="/user-prof" component={UserProfile} accessRole={["ROLE_NURSE", "ROLE_DOCTOR", "ROLE_ADMINC", "ROLE_PATIENT"]} />
            <Route exact path="/page-not-found" component={() => <h1>Page not found!</h1>} />
            <Route exact path="/unauthorized" component={Unauthorized} />
            <Redirect from="*" to="/page-not-found" />
        </Switch>
    );
};

export default AppRouter;