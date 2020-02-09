import React from 'react';
import { Container, Row , Col,Table } from 'react-bootstrap';

const Pricelist = () => {
    return(
        <Container>
             <Row >
                <Col md={{ span:10, offset:1 }} xs={12}>
                    <h3 className="border-bottom">Pricelist</h3>
                </Col>
            </Row>
            <Row>
            <Col md={{ span:10,offset:1}} xs={12}>
                <Table responsive>
                    <thead>
                        <tr>
                        <th>Appointment</th>
                        <th>Price</th>
                        </tr>
                    </thead>
                    <tbody>
                        <tr>
                        <td>Vadjenje zuba</td>
                        <td>100</td>
                        </tr>
                        <tr>
                        <td>Vadjenje krvi</td>
                        <td>50</td>
                        </tr>
                        
                    </tbody>
                </Table>
                </Col>
            </Row>
        </Container>
    );
}

export default Pricelist;