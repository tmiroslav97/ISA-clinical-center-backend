import React from 'react';
import { Container, Row, Col, Spinner, Table } from 'react-bootstrap';
import { useSelector } from 'react-redux';
import { medicinesSelector, isFetchMedicinesSelector } from '../../store/medicine_diagnose/selectors';


const MedicineTable = () => {
    const isFetchMedicines = useSelector(isFetchMedicinesSelector);
    const medicines = useSelector(medicinesSelector);


    if (!isFetchMedicines) {
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
                                medicines.map((medicine, index) => {
                                    return (
                                        <tr key={medicine.id}>
                                            <td>{medicine.code}</td>
                                            <td>{medicine.name}</td>
                                            <td>{medicine.description}</td>
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

export default MedicineTable;