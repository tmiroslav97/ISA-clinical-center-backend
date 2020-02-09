import React, { useEffect } from 'react';
import { Container, Row, Col, Spinner, Table } from 'react-bootstrap';
import { useDispatch, useSelector } from 'react-redux';
import { fetchCodebookData } from '../../store/medicine_diagnose/actions';
import { codebookDataSelector, isFetchCodebookSelector } from '../../store/medicine_diagnose/selectors';


function MedicineDiagnose() {
    const dispatch = useDispatch();
    const isFetchCodebook = useSelector(isFetchCodebookSelector);
    const codebook = useSelector(codebookDataSelector);

    useEffect(() => {
        dispatch(
            fetchCodebookData({})
        );
    }, []);

    if (!isFetchCodebook) {
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
                    <h3>Medicines</h3>
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
                                codebook.map((medicine, index) => {
                                    if (medicine.type == 'Medicine') {
                                        return (
                                            <tr key={medicine.id}>
                                                <td>{medicine.code}</td>
                                                <td>{medicine.name}</td>
                                                <td>{medicine.description}</td>
                                            </tr>
                                        );
                                    }
                                })
                            }
                        </tbody>
                    </Table>
                </Col>
            </Row>
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
                                codebook.map((diagnose, index) => {
                                    if (diagnose.type == 'Diagnose') {
                                        return (
                                            <tr key={diagnose.id}>
                                                <td>{diagnose.code}</td>
                                                <td>{diagnose.name}</td>
                                                <td>{diagnose.description}</td>
                                            </tr>
                                        );
                                    }
                                })
                            }
                        </tbody>
                    </Table>
                </Col>
            </Row>
        </Container>
    );
}

export default MedicineDiagnose;