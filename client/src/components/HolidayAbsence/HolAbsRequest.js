import React, { useState } from 'react';
import { useDispatch, useSelector } from 'react-redux';
import { Row, Form, Col, Button, Container } from 'react-bootstrap';
import moment from 'moment';
import HolAbsRequestsTable from './HolAbsRequestsTable';
import { absHolRequest } from '../../store/absence_holiday/actions';
import { userDataSelector } from '../../store/user/selectors';

const HolAbsRequest = () => {
    const dispatch = useDispatch();
    const data = useSelector(userDataSelector);
    const clinicId = data.clinicId;
    const personnelId = data.id;
    const [validated, setValidated] = useState(false);
    const [today, setToday] = useState(moment().format('YYYY-MM-DD'));
    const [startDate, setStartDate] = useState(moment().format('YYYY-MM-DD'));
    const [endDate, setEndDate] = useState(moment().format('YYYY-MM-DD'));
    const [type, setType] = useState('Absence');


    const handleSubmit = (event) => {

        const form = event.currentTarget;
        event.preventDefault();
        if (form.checkValidity() === false) {
            event.stopPropagation();
            setValidated(true);
        } else {
            dispatch(
                absHolRequest({
                    startDate,
                    endDate,
                    type,
                    personnelId,
                    clinicId
                })
            );
            setValidated(false);
        }
    };

    return (
        <Container>
            <Row>
                <Col md={{ span: 5, offset: 3 }} xs={12}>
                    <h3 align="center" className="border-bottom">Holiday/absence request</h3>
                </Col>
            </Row>
            <Row>
                <Col md={{ span: 3, offset: 4 }} xs={12}>
                    <Form noValidate validated={validated} onSubmit={handleSubmit}>
                        <Form.Group as={Col}>
                            <div align="center">
                                <Form.Label>Type of request</Form.Label>
                            </div>
                            <Form.Control required as="select" onChange={({ currentTarget }) => {
                                setType(currentTarget.value);
                            }} >
                                <option key="0" value="Absence">Absence</option>
                                <option key="1" value="Holiday">Holiday</option>

                            </Form.Control>
                        </Form.Group>
                        <Form.Group as={Col} >
                            <div align="center">
                                <Form.Label>Choose start date:</Form.Label>
                            </div>
                            <Form.Control required type="date" min={today} id="date1"
                                onChange={({ currentTarget }) => {
                                    setStartDate(moment(currentTarget.value).format('YYYY-MM-DD'));
                                }} />

                        </Form.Group>
                        <Form.Group as={Col} >
                            <div align="center">
                                <Form.Label>Choose end date:</Form.Label>
                            </div>
                            <Form.Control required type="date" min={today} id="date2"
                                onChange={({ currentTarget }) => {
                                    setEndDate(moment(currentTarget.value).format('YYYY-MM-DD'));
                                }}
                            />
                        </Form.Group>
                        <Form.Group as={Col}>
                            <div align="center">
                                <Button variant="primary" type="submit">
                                    Submit
                                </Button>
                            </div>
                        </Form.Group>
                    </Form>
                </Col>
            </Row>
            <HolAbsRequestsTable personnelId={personnelId} />
        </Container>
    );
}

export default HolAbsRequest;