import React from 'react';
import {Container, Row, Col, Table} from 'react-bootstrap';

const AvgDocctorRating = () => {
    return(
        <Container>
            <Row >
                <Col md={{ span:10, offset:1 }} xs={12}>
                    <h3 className="border-bottom">Average doctors rating</h3>
                </Col>
            </Row>
            <Row>
                    <Col md={{span:10, offset:1}} xs={12}>
                    <Table responsive>
                        <thead>
                            <tr>
                            <th>#</th>
                            <th>First Name</th>
                            <th>Last Name</th>
                            <th>Rating</th>
                            <th>No. of reviewers</th>
                            </tr>
                        </thead>
                        <tbody>
                            <tr>
                            <td>1</td>
                            <td>Pera</td>
                            <td>Peric</td>
                            <td>5</td>
                            <td>3</td>
                            </tr>
                            <tr>
                            <td>2</td>
                            <td>Ana</td>
                            <td>Kuk</td>
                            <td>4.5</td>
                            <td>2</td>
                            </tr>
                            <tr>
                            <td>3</td>
                            <td>Ivo</td>
                            <td>Tot</td>
                            <td>3</td>
                            <td>1</td>
                            </tr>
                        </tbody>
                    </Table>
                </Col>
            </Row>
        </Container>
    );
}

export default AvgDocctorRating;