import React from 'react';
import { useSelector } from 'react-redux'
import { Table, Row, Col, Container, Spinner } from 'react-bootstrap';
import { patientsSelector, isFetchPatientsSelector } from '../../store/patients/selectors';


const PatientList = () => {
    const patients = useSelector(patientsSelector);
    const isFetchPatients = useSelector(isFetchPatientsSelector);

    if (!isFetchPatients) {
        return <div className="d-flex justify-content-center">
            <Spinner animation="border" role="status">
                <span className="sr-only">Loading...</span>
            </Spinner>
        </div>;
    }

    return (
        <Container>
            <Row >
                <Col md={{ span: 10, offset: 1 }} xs={12}>
                    <h3 className="border-bottom">Patients list</h3>
                </Col>
            </Row>
            <Row>
                <Col md={{ span: 10, offset: 1 }} xs={12}>
                    <Table responsive>
                        <thead>
                            <tr>
                                <th>First Name</th>
                                <th>Last Name</th>
                                <th>Email</th>
                                <th>Phone</th>
                                <th>Address</th>
                                <th>City</th>
                                <th>Country</th>
                                <th>UNOIP</th>
                            </tr>
                        </thead>
                        <tbody>
                            {
                                patients.map((patient, index) => {
                                    return (
                                        <tr key={index}>
                                            <td>{patient.firstName}</td>
                                            <td>{patient.lastName}</td>
                                            <td>{patient.email}</td>
                                            <td>{patient.phoneNum}</td>
                                            <td>{patient.address}</td>
                                            <td>{patient.city}</td>
                                            <td>{patient.country}</td>
                                            <td>{patient.unoip}</td>

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
export default PatientList;