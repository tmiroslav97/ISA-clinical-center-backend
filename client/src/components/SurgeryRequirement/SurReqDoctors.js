import React, { useState, useEffect } from 'react';
import { Container, Row, Col, Spinner, Table, Form, Button } from 'react-bootstrap';
import { useSelector, useDispatch } from 'react-redux';
import PickedSurReq from './PickedSurReq';
import { pickSurReqSelector, pickedSurReqSelector, pickTermSelector, pickedTermSelector, pickedRoomSelector } from '../../store/sur_req/selectors';
import { fetchFinishReservation } from '../../store/sur_req/actions';
import { userDataSelector } from '../../store/user/selectors';
import { doctorsDataSelector, isFetchDoctorsSelector } from '../../store/doctors/selectors';
import { fetchDoctorsData } from '../../store/doctors/actions';

const SurReqDoctors = () => {
    const dispatch = useDispatch();
    const pickSurReq = useSelector(pickSurReqSelector);
    const pickedSurReq = useSelector(pickedSurReqSelector);
    const pickTerm = useSelector(pickTermSelector);
    const pickedTerm = useSelector(pickedTermSelector);
    const pickedRoom = useSelector(pickedRoomSelector);
    const data = useSelector(userDataSelector);
    const clinicId = data.clinicId;
    const doctors = useSelector(doctorsDataSelector);
    const isFetchDoctors = useSelector(isFetchDoctorsSelector);
    const [chosenDoc, setChosenDoc] = useState([]);

    useEffect(() => {
        dispatch(
            fetchDoctorsData({
                clinicId
            })
        );
    }, [clinicId]);

    const handleFinishReservation = () => {
        dispatch(
            fetchFinishReservation({
                pickedSurReq,
                pickedTerm,
                pickedRoom,
                chosenDoc
            })
        );
    };

    if (!isFetchDoctors) {
        return <div className="d-flex justify-content-center">
            <Spinner animation="border" role="status">
                <span className="sr-only">Loading...</span>
            </Spinner>
        </div>;
    }

    return (
        <Container>
            {
                pickSurReq ?
                    <PickedSurReq pickedSurReq={pickedSurReq} /> : null
            }
            {
                pickTerm ?
                    <Row>
                        <Col md={{ span: 10, offset: 1 }} xs={12}>
                            <h2 className="border-bottom">Picked term</h2>
                        </Col>
                        <Col md={{ span: 3, offset: 1 }} xs={12}>
                            <Table responsive>
                                <thead>
                                    <tr>
                                        <th>Picked term:</th>
                                    </tr>
                                </thead>
                                <tbody>
                                    <tr >
                                        <td>{pickedTerm}</td>
                                    </tr>
                                </tbody>
                            </Table>
                        </Col>
                    </Row> : null
            }
            <Row>
                <Col md={{ span: 10, offset: 1 }} xs={12}>
                    <h2>Pick doctors</h2>
                </Col>
            </Row>
            <Row>
                <Col md={{ span: 3, offset: 1 }} xs={12}>
                    <Form>
                        <Form.Group as={Col}>
                            <Form.Control as="select" multiple onChange={({ currentTarget }) => {
                                let options = currentTarget.options;
                                let vals = [];
                                for (let i = 0; i < options.length; i++) {
                                    if (options[i].selected) {
                                        vals.push(options[i].value);
                                    }
                                }
                                setChosenDoc(vals);
                            }} >                                {
                                    doctors.map((doctor, index) => {
                                        if (pickedSurReq != null) {
                                            if (pickedSurReq.doctorId != doctor.id) {
                                                return (
                                                    <option key={index} value={doctor.id}>{doctor.firstName} {doctor.lastName}</option>
                                                );
                                            }
                                        }
                                    })
                                }
                            </Form.Control>
                            <Button onClick={handleFinishReservation} className="mt-2">Finish reservation</Button>
                        </Form.Group>
                    </Form>
                </Col>
            </Row>
        </Container >
    );
}

export default SurReqDoctors;