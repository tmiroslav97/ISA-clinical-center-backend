import React, { useState, useEffect } from 'react';
import useStateWithCallback from 'use-state-with-callback';
import { Container, Row, Col, Spinner, Table, Pagination, PageItem } from 'react-bootstrap';
import { useDispatch, useSelector } from 'react-redux';
import { doctorsDataSelector, isFetchDoctorsSelector } from '../../store/doctors/selectors';

const DoctorList = ({ filterTerm, cnt }) => {
    const doctors = useSelector(doctorsDataSelector);
    const isFetchDoctorsData = useSelector(isFetchDoctorsSelector);


    if(!isFetchDoctorsData) {
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
                    <h3>Doctors list</h3>
                </Col>
            </Row>
            <Row>
                <Col md={{ span: 10, offset: 1 }} xs={12}>
                    <Table responsive>
                        <thead>
                            <tr>
                                <th>#</th>
                                <th>First Name</th>
                                <th>Last Name</th>
                                <th>Average rating</th>
                            </tr>
                        </thead>
                        <tbody>
                            {
                                doctors.filter(doctor => doctor.type.includes(filterTerm)).map((doctor, index) => {
                                    return (
                                        <tr key={doctor.id}>
                                            <td>{cnt * 10 + index + 1}</td>
                                            <td>{doctor.firstName}</td>
                                            <td>{doctor.lastName}</td>
                                            <td>{doctor.averageRating}</td>
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

export default DoctorList;