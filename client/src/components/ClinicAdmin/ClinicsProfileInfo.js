import React from 'react';
import {Container, Row, Col, Form, Button} from 'react-bootstrap';

const ClinicsProfileInfo = () =>{
    return(
        <Container>
            <Row>
                <Col md={{ span:4, offset: 3 }} xs={12}>
                    <Form>
                        <Form.Group controlId="formBasicCname">
                            <Form.Label>Clinic's name</Form.Label>
                            <Form.Control type="text"  />
                        </Form.Group>

                        <Form.Group controlId="formBasicCaddress">
                            <Form.Label>Address</Form.Label>
                            <Form.Control type="text"/>
                            <Form.Label>*****Mapa fali*****</Form.Label>
                        </Form.Group>

                        <Form.Group controlId="formBasicCdescription">
                            <Form.Label>Description</Form.Label>
                            <Form.Control type="text"/>
                        </Form.Group>


                        <Button variant="primary" >
                            Edit
                        </Button>
                    </Form>
                </Col>
            </Row>
        </Container>
    );
}

export default ClinicsProfileInfo;