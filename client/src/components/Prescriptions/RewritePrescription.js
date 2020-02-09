import React, { useState, useEffect } from 'react';
import { useDispatch, useSelector } from 'react-redux';
import { Row, Col, Button, Container, Table } from 'react-bootstrap';
import { prescriptionsDataSelector } from '../../store/prescriptions/selectors';
import { fetchPrescriptions, rewritePrescription } from '../../store/prescriptions/actions';
import { userDataSelector } from '../../store/user/selectors';


const RewritePrescription = () => {
    const dispatch = useDispatch();
    const prescriptions = useSelector(prescriptionsDataSelector);
    const data = useSelector(userDataSelector);
    const nurseId = data.id;
    const clinicId = data.clinicId;
    const handleRewrite = (prescriptionId) => {
        dispatch(
            rewritePrescription({
                nurseId,
                prescriptionId,
                clinicId
            })
        );
    };

    useEffect(() => {
        if (nurseId != null) {
            dispatch(
                fetchPrescriptions({clinicId})
            );
        }
    }, [nurseId]);

    return (
        <Container>
            <Row>
                <Col md={{ span: 10, offset: 1 }} xs={12}>
                    <h3>Prescriptions for rewrite</h3>
                </Col>
            </Row>
            <Row>
                <Col md={{ span: 10, offset: 1 }} xs={12}>
                    <Table responsive>
                        <thead>
                            <tr>
                                <th>Medicine code</th>
                                <th>Medicine name</th>
                                <th>Rewrite</th>
                            </tr>
                        </thead>
                        <tbody>
                            {
                                prescriptions.map((prescription, index) => {
                                    return (
                                        <tr key={index}>
                                            <td>{prescription.medicineCode}</td>
                                            <td>{prescription.medicineName}</td>
                                            <td>
                                                <Button variant="primary" onClick={() => handleRewrite(prescription.id)}>
                                                    Rewrite
                                                    </Button>
                                            </td>
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

export default RewritePrescription;