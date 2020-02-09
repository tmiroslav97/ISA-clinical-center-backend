import React, { useState, useEffect } from 'react';
import { Container, Row, Col, Spinner, Table, Button, Modal, Form } from 'react-bootstrap';
import { useDispatch, useSelector } from 'react-redux';
import { isFetchMedicalRecordSelector, medicalRecordSelector } from '../../store/medical_record/selectors';
import { fetchMedicalRecordByApp, editMedicalRecord } from '../../store/medical_record/actions';
import { fetchPatientByApp } from '../../store/patients/actions';
import { isFetchPatientsSelector, patientSelector } from '../../store/patients/selectors';
import MedicalReport from '../MedicalReport/MedicalReport';


const MedicalRecord = ({ match }) => {
    const dispatch = useDispatch();
    const isFetchMedicalRecord = useSelector(isFetchMedicalRecordSelector);
    const medicalRecord = useSelector(medicalRecordSelector);
    const typeId = match.params.typeId;
    const patient = useSelector(patientSelector);
    const isFetchPatient = useSelector(isFetchPatientsSelector);
    const [show, setShow] = useState(false);
    const [bloodType, setBloodType] = useState('');
    const [weight, setWeight] = useState('');
    const [height, setHeight] = useState('');
    const [description, setDescription] = useState('');
    const [validated, setValidated] = useState(false);


    useEffect(() => {
        if (typeId != null) {
            dispatch(
                fetchMedicalRecordByApp({
                    typeId
                })
            );

            dispatch(
                fetchPatientByApp({
                    typeId
                })
            );
        }
    }, [typeId]);

    const handleEditMedicalRecord = (event) => {
        const form = event.currentTarget;
        event.preventDefault();
        if (form.checkValidity() === false) {
            event.stopPropagation();
            setValidated(true);
        } else {
            dispatch(
                editMedicalRecord({
                    id: medicalRecord.id,
                    bloodType,
                    weight,
                    height,
                    description
                })
            );
            setValidated(false);
            setShow(false);
        }
    };

    const handleClose = () => setShow(false);
    const handleShow = () => {
        setBloodType(medicalRecord.bloodType);
        setWeight(medicalRecord.weight);
        setHeight(medicalRecord.height);
        setDescription(medicalRecord.description);
        setShow(true);
    };

    if (!isFetchMedicalRecord || !isFetchPatient) {
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
                    <Form noValidate validated={validated} onSubmit={handleEditMedicalRecord}>
                        <Form.Group>
                            <Form.Label>Blood type:</Form.Label>
                            <Form.Control required type="text" id="txtBloodType" defaultValue={bloodType}
                                onChange={({ currentTarget }) => {
                                    setBloodType(currentTarget.value);
                                }}
                            />
                            <Form.Label>Weight:</Form.Label>
                            <Form.Control required type="number" min="0" step="0.01" id="txtWeight" defaultValue={weight}
                                onChange={({ currentTarget }) => {
                                    setWeight(currentTarget.value);
                                }}
                            />
                            <Form.Label>Height:</Form.Label>
                            <Form.Control required type="number" min="0" step="0.01" id="txtHeight" defaultValue={height}
                                onChange={({ currentTarget }) => {
                                    setHeight(currentTarget.value);
                                }}
                            />
                            <Form.Label>Description:</Form.Label>
                            <Form.Control required as="textarea" id="txtDescription" rows="4" defaultValue={description}
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
                    <h3 className="border-bottom">Patient information</h3>
                </Col>
            </Row>
            <Row>
                <Col md={{ span: 10, offset: 1 }} xs={12}>
                    <Table responsive>
                        <tbody>
                            <tr>
                                <th>First name</th>
                                <td align="right">{patient.firstName}</td>
                            </tr>
                            <tr>
                                <th>Last name</th>
                                <td align="right">{patient.lastName}</td>
                            </tr>
                            <tr>
                                <th>UNOIP</th>
                                <td align="right">{patient.unoip}</td>
                            </tr>
                        </tbody>
                    </Table>
                </Col>
            </Row>
            <Row>
                <Col md={{ span: 10, offset: 1 }} xs={12}>
                    <h3 className="border-bottom">Medical record</h3>
                </Col>
            </Row>
            <Row>
                <Col md={{ span: 10, offset: 1 }} xs={12}>
                    <Table responsive>
                        <tbody>
                            <tr>
                                <th>Blood type</th>
                                <td align="right">{medicalRecord.bloodType}</td>
                            </tr>
                            <tr>
                                <th>Weight</th>
                                <td align="right">{medicalRecord.weight}</td>
                            </tr>
                            <tr>
                                <th>Height</th>
                                <td align="right">{medicalRecord.height}</td>
                            </tr>
                            <tr>
                                <th>Description</th>
                                <td align="right">{medicalRecord.description}</td>
                            </tr>
                            <tr>
                                <td colSpan="2" align="right">
                                    <Button variant="primary" onClick={() => { handleShow(); }}>Edit</Button>
                                </td>
                            </tr>
                        </tbody>
                    </Table>
                </Col>
            </Row>
            <MedicalReport typeId={typeId} medRecId={medicalRecord.id} patientId={patient.id} />
        </Container>
    );
}

export default MedicalRecord;