import React from 'react';
import { Form, Button, Container, Row ,Col} from 'react-bootstrap';

function UpdateProfile(){
    return(
        <Container>
            <Row>
                <Col md={{ span:4, offset:1 }} xs={12}>
                <Form >
                        <Form.Group controlId="formBasicCity">
                            <Form.Label>City</Form.Label>
                            <Form.Control type="text" />
                        </Form.Group>

                        <Form.Group controlId="formBasicAddress">
                            <Form.Label>Address</Form.Label>
                            <Form.Control type="text" />
                        </Form.Group>
                        <Form.Group controlId="formBasicCountry">
                            <Form.Label>Country</Form.Label>
                            <Form.Control type="text" />
                        </Form.Group>
                        <Form.Group controlId="formBasicAFirstName">
                            <Form.Label>First Name</Form.Label>
                            <Form.Control type="text" />
                        </Form.Group>
                        <Form.Group controlId="formBasicLastName">
                            <Form.Label>Last Name</Form.Label>
                            <Form.Control type="text" />
                        </Form.Group>
                        <Button variant="primary" type="submit">
                            Update
                        </Button>
                    </Form>
                </Col>
            </Row>
        </Container>
        
    );
}

export default UpdateProfile;