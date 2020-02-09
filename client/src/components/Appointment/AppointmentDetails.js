import React from 'react';
import { Container, Row, Col, Spinner, Table, Button } from 'react-bootstrap';
import { history } from '../../index';
import moment from 'moment'
import { useDispatch, useSelector } from 'react-redux';
import { appointmentSelector, isFetchAppointmentSelector } from '../../store/appointments/selectors';



const AppointmentDetails = ({ start, typeId }) => {
    const isFetchAppointment = useSelector(isFetchAppointmentSelector);
    const appointment = useSelector(appointmentSelector);

    if (!isFetchAppointment) {
        return <div className="d-flex justify-content-center">
            <Spinner animation="border" role="status">
                <span className="sr-only">Loading...</span>
            </Spinner>
        </div>;
    }

    return (
        <Container>
            <Row>
                <Col md={{ span: 10, offset: 1 }} xs={12}>
                    <h3 className="border-bottom">Appointment details</h3>
                </Col>
            </Row>
            <Row>
                <Col md={{ span: 10, offset: 1 }} xs={12}>
                    <Table responsive>
                        <tbody>
                            <tr>
                                <th>Patient</th>
                                <td align="right">{appointment.patient}</td>
                            </tr>
                            {
                                isFetchAppointment && appointment.appState !== 'FINISHED' && appointment.appState !== 'STARTED' && moment(start).format('YYYY-MM-DD') === moment().format('YYYY-MM-DD') &&
                                <tr>
                                    <th>Start appointment</th>
                                    <td colSpan="2" align="right">
                                        <Button variant="primary" onClick={() => { history.push('/medical-rec/' + typeId); }}>Start</Button>
                                    </td>
                                </tr>
                            }
                        </tbody>
                    </Table>
                </Col>
            </Row>
        </Container >
    );
}

export default AppointmentDetails;