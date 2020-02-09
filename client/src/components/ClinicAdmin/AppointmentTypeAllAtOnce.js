import React, { useState, useEffect } from 'react';
import { Table, Container, Col, Row, Form, Button, Modal, Spinner } from 'react-bootstrap';
import { useDispatch, useSelector } from 'react-redux';
import { addAppointmentType, fetchAppointmentType, deleteAppointmentType, editAppointmentType, searchAppointmentType } from '../../store/appointments/actions';
import { appointmentTypeSelector, isFetchAppointmentTypeSelector } from '../../store/appointments/selectors';
import { userDataSelector } from '../../store/user/selectors';


const AppointmentTypAllAtOnce = () => {
    const dispatch = useDispatch();
    const [type, setType] = useState('');
    const [typeId, setTypeId] = useState(0);
    const [cliId, setCliId] = useState(0);
    const appointmentTypes = useSelector(appointmentTypeSelector);
    const isFetchAppointmentTypes = useSelector(isFetchAppointmentTypeSelector);
    const data = useSelector(userDataSelector);
    const clinicId = data.clinicId;
    const [show1, setShow1] = useState(false);
    const [show2, setShow2] = useState(false);

    const handleShow2 = () => setShow2(true);

    const handleEdit = () => {
        dispatch(
            editAppointmentType({id:typeId, type, clinicId})
        );
        setShow1(false);
    }

    const handleEditShow  = (appointment)=>{
        setType(appointment.type);
        setTypeId(appointment.id);
        setShow1(true);
    }

    const handleDelete = (appointment) => {
        //console.log(appointment);
        dispatch(
            deleteAppointmentType({id:appointment.id, clinicId})
        );
    }

    const handleAddAppointmentType = () => {
        setCliId(clinicId);
        dispatch(
            addAppointmentType({
                type,  clinicId
            })
        );
        setShow2(false);
    };
    //console.log(clinicId);

    const handleSearchAppointmentType = () => {
        dispatch(
            searchAppointmentType({type, clinicId})
        );
    };

    useEffect(() => {
        dispatch(
            fetchAppointmentType({clinicId})
        );
    }, [clinicId]);

    if (!isFetchAppointmentTypes) {
        return <div className="d-flex justify-content-center">
            <Spinner animation="border" role="status">
                <span className="sr-only">Loading...</span>
            </Spinner>
        </div>;
    }

    return (
        <>
            <Modal show={show1} onHide={handleEdit} animation={false}>
                <Modal.Header closeButton>
                    <Modal.Title>Edit data:</Modal.Title>
                </Modal.Header>
                <Modal.Body>
                    <Form>
                        <Form.Group as={Col}>
                            <Form.Label>Type:</Form.Label>
                            <Form.Control type="text" value={type} onChange={({ currentTarget }) => {
                                setType(currentTarget.value);}} />
                        </Form.Group>
                    </Form>
                </Modal.Body>
                <Modal.Footer>
                    <Button variant="primary" onClick={handleEdit}>
                        Edit
          </Button>
                </Modal.Footer>
            </Modal>

            <Modal show={show2} onHide={handleAddAppointmentType} animation={false}>
                <Modal.Header closeButton>
                    <Modal.Title>Add data:</Modal.Title>
                </Modal.Header>
                <Modal.Body>
                    <Form>
                        <Form.Group as={Col}>
                            <Form.Label>Type:</Form.Label>
                            <Form.Control type="text" placeholder="Enter a new type" onChange={({ currentTarget }) => {
                                setType(currentTarget.value);
                            }} />
                        </Form.Group>
                    </Form>
                </Modal.Body>
                <Modal.Footer>
                    <Button variant="primary" onClick={handleAddAppointmentType} >
                        Add
          </Button>
                </Modal.Footer>
            </Modal>



            <Container>
                <Row >
                    <Col md={{ span: 10, offset: 1 }} xs={12}>
                        <h3 className="border-bottom">Appointment types</h3>
                    </Col>
                </Row>
                <Row >
                    <Col md={{ span: 5, offset: 1 }} xs={12}>
                        <Form>
                            <Form.Group as={Row} >

                                <Form.Label>Add new appointment type:</Form.Label>
                                <Col>
                                    <Button onClick={handleShow2} >Add </Button>
                                </Col>
                            </Form.Group>

                            <Form.Group as={Row} >

                                <Form.Label>Search appointment types:</Form.Label>
                                <Col>
                                    <Form.Control type="text" placeholder="Search "  onChange={({ currentTarget }) => {
                                setType(currentTarget.value);}}/>
                                </Col>
                                <Col>
                                    <Button onClick={handleSearchAppointmentType}>Search</Button>
                                </Col>
                            </Form.Group>


                        </Form>
                    </Col>
                </Row>
                <Row  >
                    <Col md={{ span: 10, offset: 1 }} xs={12}>
                        <Table responsive>
                            <thead>
                                <tr>
                                    <th>#</th>
                                    <th>Type</th>
                                    <th>Edit</th>
                                    <th>Delete</th>
                                </tr>
                            </thead>
                            <tbody>
                                {
                                    appointmentTypes.map((appointment, index) => {
                                        return (
                                            <tr key={appointment.id}>
                                                <td>{index + 1}</td>
                                                <td>{appointment.type}</td>
                                                <td><Button variant="success" onClick={()=>{handleEditShow(appointment)}}>Edit</Button></td>
                                                <td><Button variant="danger" onClick={()=>{handleDelete(appointment);}}>Delete</Button></td>
                                            </tr>
                                        );
                                    })
                                }
                            </tbody>
                        </Table>
                    </Col>
                </Row>
            </Container >
        </>
    );
}

export default AppointmentTypAllAtOnce;