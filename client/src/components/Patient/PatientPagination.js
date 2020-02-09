import React, { useState, useEffect } from 'react';
import { Container, Row, Col, Pagination, Form, Button } from 'react-bootstrap';
import { useDispatch, useSelector } from 'react-redux';
import { fetchPatientsPagination } from '../../store/patients/actions';
import { pageCntSelector, pageSelCntSelector } from '../../store/common/selectors';
import { userDataSelector } from '../../store/user/selectors';
import PatientList from './PatientList';


const PatientPagination = () => {
    const dispatch = useDispatch();
    const data = useSelector(userDataSelector);
    const patientPageCnt = useSelector(pageCntSelector);
    const selpageCnt = useSelector(pageSelCntSelector);
    const [pageCnt, setPageCnt] = useState(selpageCnt);
    const [sort, setSort] = useState(0);

    useEffect(() => {
        dispatch(
            fetchPatientsPagination({
                clinicId: data.clinicId,
                pageCnt,
                sort
            })
        );
    }, [pageCnt, sort]);

    let items = [];
    for (let number = 1; number <= patientPageCnt; number++) {
        items.push(
            <Pagination.Item key={number} active={number == (selpageCnt + 1)}>
                {number}
            </Pagination.Item>
        );
    }

    const handlePagination = (e) => {
        e.preventDefault();
        let event = e.target.text;
        if (event != undefined && patientPageCnt > 0) {
            if (event.includes('First')) {
                setPageCnt(0);
            } else if (event.includes('Last')) {
                setPageCnt(patientPageCnt - 1);
            } else if (event.includes('Next')) {
                if (pageCnt < patientPageCnt - 1) {
                    setPageCnt(pageCnt + 1);
                }
            } else if (event.includes('Previous')) {
                if (pageCnt > 0) {
                    setPageCnt(pageCnt - 1);
                }
            } else {
                setPageCnt(event - 1);
            }
        }
    };

    return (
        <Container>
            <Row >
                <Col md={{ span: 10, offset: 1 }} xs={12}>
                    <h3 className="border-bottom">Patients list</h3>
                </Col>
            </Row>
            <Row>
                <Col md={{ span: 10, offset: 1 }} xs={12}>

                    <Form onSubmit={(event)=>{event.preventDefault();}}>
                        <Form.Row>
                            <Form.Group as={Col} md="3">
                                <Form.Label>Sort by:</Form.Label>
                                <Form.Control as="select" onChange={({ currentTarget }) => {
                                    setSort(currentTarget.value);
                                }} >
                                    <option value="0">-</option>
                                    <option value="1">First name (asc)</option>
                                    <option value="2">First name (desc)</option>
                                    <option value="3">Last name (asc)</option>
                                    <option value="4">Last name (desc)</option>
                                    <option value="5">UNOIP (asc)</option>
                                    <option value="6">UNOIP (desc)</option>
                                </Form.Control>
                            </Form.Group>
                        </Form.Row>
                    </Form>
                </Col>
            </Row>
            <PatientList />
            <Row>
                <Col md={{ span: 10, offset: 1 }} xs={12}>
                    <Pagination onClick={handlePagination} className="pagination justify-content-center mb-5">
                        <Pagination.First />
                        <Pagination.Prev />
                        {items}
                        <Pagination.Next />
                        <Pagination.Last />
                    </Pagination>
                </Col>
            </Row>
        </Container>
    );
}

export default PatientPagination;