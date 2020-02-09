import React, { useState, useEffect } from 'react';
import useStateWithCallback from 'use-state-with-callback';
import { Container, Row, Col, Spinner, Table, Pagination, PageItem } from 'react-bootstrap';
import { useDispatch, useSelector } from 'react-redux';
import { clinicsDataSelector, isFetchClinicsDataSelector} from '../../store/clinics/selectors';

const ClinicList = ({ filterTerm, cnt }) => {
    const clinics = useSelector(clinicsDataSelector);
    const isFetchClinicsData = useSelector(isFetchClinicsDataSelector);

    if (!isFetchClinicsData) {
        return <div className="d-flex justify-content-center">
            
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
                                <th>#</th>
                                <th>Name</th>
                                <th>Address</th>
                                <th>Average rating</th>
                                <th>Price</th>
                            </tr>
                        </thead>
                        <tbody>
                            {
                                clinics.filter(clinicsDto => clinicsDto.clinic.type.includes(filterTerm)).map((clinicsDto,index) => {
                                    return (
                                        <tr key={clinicsDto.clinic.id}>
                                            <td>{cnt * 10 + index + 1}</td>
                                            <td>{clinicsDto.clinic.name}</td>
                                            <td>{clinicsDto.clinic.address}</td>
                                            <td>{clinicsDto.clinic.averageRating}</td>
                                            <td>{clinicsDto.clinic.price}</td>
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

export default ClinicList;