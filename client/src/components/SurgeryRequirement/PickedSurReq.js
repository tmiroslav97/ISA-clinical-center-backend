import React from 'react';
import { Container, Row, Col, Table } from 'react-bootstrap';

const PickedSurReq = ({ pickedSurReq }) => {

    return (
        <Container>
            <Row>
                <Col md={{ span: 10, offset: 1 }} xs={12}>
                    <h2 className="border-bottom">Chosen surgery requirement</h2>
                </Col>
            </Row>
            <Row>
                <Col md={{ span: 10, offset: 1 }} xs={12}>
                    <Table responsive>
                        <thead>
                            <tr>
                                <th>Patient</th>
                                <th>Doctor</th>
                                <th>Required surgery date</th>
                                <th>Term</th>
                            </tr>
                        </thead>
                        <tbody>

                            <tr key={pickedSurReq.id}>
                                <td>{pickedSurReq.patientName}</td>
                                <td>{pickedSurReq.doctorName}</td>
                                <td>{pickedSurReq.date}</td>
                                <td>{pickedSurReq.termin}-{pickedSurReq.termin + 3}</td>
                            </tr>
                        </tbody>
                    </Table>
                </Col>
            </Row>


        </Container>
    );

}

export default PickedSurReq;