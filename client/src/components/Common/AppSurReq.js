import React, { useState, useEffect } from 'react';
import { Container, Row, Col, Spinner, Table, Button, Modal, Form } from 'react-bootstrap';



const AppSurReq = ({ medRep = false, patientId = null }) => {
    return (
        <Container>
            <Row>
                <Col md={{ span: 10, offset: 1 }} xs={12}>
                    <h3 className="border-bottom">Book surgery or appointment</h3>
                </Col>
            </Row>

        </Container>
    );
}

export default AppSurReq;