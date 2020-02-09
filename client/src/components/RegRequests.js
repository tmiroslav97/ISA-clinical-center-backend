import React from 'react';
import { Container, Row, Col, Table, Button } from 'react-bootstrap';

function RegRequests(){
    return(
        <Container>
            <Row>
                <Col md={12} xs={12}>
                    <h1 className="border-bottom">Registration requests</h1>
                </Col>
            </Row>
            <Row>
                <Col md={12} xs={12}>
                    <Table striped bordered hover>
                        <thead>
                            <tr>
                            <th>#</th>
                            <th>First Name</th>
                            <th>Last Name</th>
                            <th>Email</th>
                            <th>Action</th>
                            </tr>
                        </thead>
                        <tbody>
                            <tr>
                                <td>1</td>
                                <td>Mark</td>
                                <td>Otto</td>
                                <td>@mdo</td>
                                <td>
                                    <Button>Details</Button>
                                </td>
                            </tr>
                            <tr>
                                <td>2</td>
                                <td>Jacob</td>
                                <td>Thornton</td>
                                <td>@fat</td>
                                <td>
                                    <Button>Details</Button>
                                </td>
                            </tr>
                            <tr>
                                <td>3</td>
                                <td>Larry the Bird</td>
                                <td>Nesto</td>
                                <td>@twitter</td>
                                <td>
                                    <Button>Details</Button>
                                </td>
                            </tr>
                        </tbody>
                    </Table>
                </Col>
            </Row>
        </Container>
    );
}

export default RegRequests;