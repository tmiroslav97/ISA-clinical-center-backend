import React, { Component } from 'react';
import {Container, Col,Row, Form} from 'react-bootstrap';

const AvgClinicRating = () => {
    return(
        <Container>
            <Row>
                <Col md={{ span:3, offset: 1 }} xs={12}>
                    <Form>
                        <Form.Group controlId="formBasicAvg">
                            <Form.Label>The average rating of the clinic:</Form.Label>
                            <Form.Control type="text"  />
                        </Form.Group>
 
                        <Form.Group controlId="formBasicNumber">
                            <Form.Label>Number of persones who rated the clinic</Form.Label>
                            <Form.Control type="text"  />
                        </Form.Group>
                    </Form>
                </Col>
            </Row>
        </Container>
    );
}

export default AvgClinicRating;