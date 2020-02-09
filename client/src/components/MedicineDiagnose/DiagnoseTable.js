import React from 'react';
import { Container, Row, Col, Spinner, Table } from 'react-bootstrap';
import { useSelector } from 'react-redux';
import { diagnosesSelector, isFetchDiagnosesSelector } from '../../store/medicine_diagnose/selectors';


const DiagnoseTable = () => {
    const isFetchDiagnoses = useSelector(isFetchDiagnosesSelector);
    const diagnoses = useSelector(diagnosesSelector);

    if (!isFetchDiagnoses) {
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
                    <h3>Diagnoses</h3>
                </Col>
            </Row>
            <Row>
                <Col md={{ span: 10, offset: 1 }} xs={12}>
                    <Table responsive>
                        <thead>
                            <tr>
                                <th>Code</th>
                                <th>Name</th>
                                <th>Description</th>
                            </tr>
                        </thead>
                        <tbody>
                            {
                                diagnoses.map((diagnose, index) => {
                                    return (
                                        <tr key={diagnose.id}>
                                            <td>{diagnose.code}</td>
                                            <td>{diagnose.name}</td>
                                            <td>{diagnose.description}</td>
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

export default DiagnoseTable;