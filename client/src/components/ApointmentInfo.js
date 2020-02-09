import React from 'react';
import { Container, Spinner, Col,Row } from 'react-bootstrap';

function ApointmentInfo(){
    return(
        <Container>
            <Col md={{span:5, offset:3}} xs={12}>
            <Row >
                <h3>Apointment info - in progress</h3>
            </Row>
            <Row>
                <Spinner animation="grow" variant="primary" />
                <Spinner animation="grow" variant="secondary" />
                <Spinner animation="grow" variant="success" />
                <Spinner animation="grow" variant="danger" />
                <Spinner animation="grow" variant="warning" />
                <Spinner animation="grow" variant="info" />
                <Spinner animation="grow" variant="light" />
                <Spinner animation="grow" variant="dark" />
            </Row>
            </Col>
        </Container>
    );
}

export default ApointmentInfo;