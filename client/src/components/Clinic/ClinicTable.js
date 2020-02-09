import React from 'react';
import { Container, Row, Col, Spinner, Table } from 'react-bootstrap';
import { useSelector } from 'react-redux';
import { clinicsDataSelector, isFetchClinicsDataSelector } from '../../store/clinics/selectors';


const ClinicTable = () => {
    const clinics = useSelector(clinicsDataSelector);
    const isFetchClinicsData = useSelector(isFetchClinicsDataSelector);

    if (!isFetchClinicsData) {
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
                    <h3>Clinics list</h3>
                </Col>
            </Row>
            <Row>
                <Col md={{ span: 10, offset: 1 }} xs={12}>
                    <Table responsive>
                        <thead>
                            <tr>
                                <th>Name</th>
                                <th>Address</th>
                                <th>Description</th>
                            </tr>
                        </thead>
                        <tbody>
                            {
                                clinics.map((clinic, index) => {
                                    return (
                                        <tr key={clinic.id}>
                                            <td>{clinic.name}</td>
                                            <td>{clinic.address}</td>
                                            <td>{clinic.description}</td>
                                        </tr>
                                    );
                                })
                            }
                        </tbody>
                    </Table>
                </Col>
            </Row>
        </Container>
    );
}

export default ClinicTable;