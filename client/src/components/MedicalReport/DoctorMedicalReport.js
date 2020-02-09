import React, { useState, useEffect } from 'react';
import { Container, Row, Col, Spinner, Table, Button, Modal, Form } from 'react-bootstrap';
import { useDispatch, useSelector } from 'react-redux';
import { isFetchMedicalReportSelector, medicalReportsSelector } from '../../store/medical_report/selectors';
import { userIdSelector } from '../../store/user/selectors';
import { fetchDoctorsMedicalReports, editMedicalReport } from '../../store/medical_report/actions';
import { fetchDiagnosesAll } from '../../store/medicine_diagnose/actions';
import { diagnosesSelector, isFetchDiagnosesSelector } from '../../store/medicine_diagnose/selectors';


const DoctorMedicalReport = () => {
    const dispatch = useDispatch();
    const doctorId = useSelector(userIdSelector);
    const medicalReports = useSelector(medicalReportsSelector);
    const isFetchMedicalReport = useSelector(isFetchMedicalReportSelector);
    const diagnoses = useSelector(diagnosesSelector);
    const isFetchDiagnose = useSelector(isFetchDiagnosesSelector);
    const [show, setShow] = useState(false);
    const [validated, setValidated] = useState(false);
    const [id, setId] = useState('');
    const [description, setDescription] = useState('');
    const [diagnoseNam, setDiagnoseNam] = useState('');
    const [diagnoseId, setDiagnoseId] = useState(null);

    useEffect(() => {
        if (doctorId != null) {
            dispatch(
                fetchDoctorsMedicalReports({
                    doctorId
                })
            );
        }
        dispatch(
            fetchDiagnosesAll({})
        );
    }, [doctorId]);

    const handleEditMedicalReport = (event) => {
        const form = event.currentTarget;
        event.preventDefault();
        if (form.checkValidity() === false) {
            event.stopPropagation();
            setValidated(true);
        } else {
            dispatch(
                editMedicalReport({
                    id,
                    description,
                    diagnoseId,
                    doctorId
                })
            );
            setValidated(false);
            setShow(false);
        }
    };

    const handleClose = () => setShow(false);
    const handleShow = (medRepId, description, diagnose) => {
        setId(medRepId);
        setDiagnoseNam(diagnose);
        setDescription(description);
        setShow(true);
    };

    if (!isFetchMedicalReport || !isFetchDiagnose) {
        return <div className="d-flex justify-content-center">
            <Spinner animation="border" role="status">
                <span className="sr-only">Loading...</span>
            </Spinner>
        </div>;
    }

    return (
        <Container>
            <Modal show={show} onHide={handleClose} animation={true}>
                <Modal.Header closeButton>
                    <Modal.Title>Edit patient medical record</Modal.Title>
                </Modal.Header>
                <Modal.Body>
                    <Form noValidate validated={validated} onSubmit={handleEditMedicalReport}>
                        <Form.Group as={Col} md="6">
                            <Form.Label>Diagnoses:</Form.Label>
                            <Form.Control required as="select" id="cbDiagnoses" onChange={({ currentTarget }) => {
                                setDiagnoseId(currentTarget.value);
                            }} >
                                <option></option>
                                {
                                    diagnoses.map((diagnose) => {
                                        let flag = false;
                                        if (diagnose.name === diagnoseNam) {
                                            flag = true;
                                        }
                                        return (
                                            <option key={diagnose.id} selected={flag} value={diagnose.id}>{diagnose.name}</option>
                                        );
                                    })
                                }
                            </Form.Control>
                        </Form.Group>
                        <Form.Group>
                            <Form.Label>Description:</Form.Label>
                            <Form.Control required as="textarea" id="txtDescription" maxlength="80" rows="4" defaultValue={description}
                            onChange={({ currentTarget }) => {
                                setDescription(currentTarget.value);
                            }}
                            />
                        </Form.Group>
                        <Form.Group as={Col} align="right">
                            <Button variant="primary" type="submit">
                                Edit
                            </Button>
                        </Form.Group>
                    </Form>
                </Modal.Body>
            </Modal>
            <Row>
                <Col md={{ span: 10, offset: 1 }} xs={12}>
                    <h3 className="border-bottom">Doctor medical report</h3>
                </Col>
            </Row>
            <Row>
                <Col md={{ span: 10, offset: 1 }} xs={12}>
                    <Table responsive>
                        <thead>
                            <tr>
                                <th>Patient name</th>
                                <th>Appointment date</th>
                                <th>Description</th>
                                <th>Diagnose name</th>
                                <th>Medicines</th>
                                <th>Action</th>
                            </tr>
                        </thead>
                        <tbody>
                            {
                                medicalReports.map((medRep) => {
                                    return (
                                        <tr key={medRep.id}>
                                            <td>{medRep.patientName}</td>
                                            <td>{medRep.appDate}</td>
                                            <td>{medRep.description}</td>
                                            <td>{medRep.diagnoseName}</td>
                                            <td>
                                                <Form.Control required as="select" id="cbMedicines" multiple>
                                                    {
                                                        medRep.medicineName.map((medName, index) => {
                                                            return (
                                                                <option key={index} diabled>{medName}</option>
                                                            );
                                                        })
                                                    }
                                                </Form.Control>
                                            </td>
                                            <td>
                                                <Button variant="primary" onClick={() => { handleShow(medRep.id, medRep.description, medRep.diagnoseName); }}>
                                                    Edit
                                                </Button>
                                            </td>
                                        </tr>
                                    );
                                })
                            }
                        </tbody>
                    </Table>
                </Col>
            </Row>
        </Container >
    );
}

export default DoctorMedicalReport;