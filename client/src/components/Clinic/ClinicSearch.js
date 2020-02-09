import React, { useEffect, useState } from 'react';
import useStateWithCallback from 'use-state-with-callback';
import { Container, Row, Col, Table, Form, Button, Pagination } from 'react-bootstrap';
import { useDispatch, useSelector } from 'react-redux';
import moment from 'moment';
import { fetchClinicsData, searchClinic } from '../../store/clinics/actions';
import { pageCountSelector } from '../../store/clinics/selectors';
import ClinicList from './ClinicList';
import { fetchPatientData } from '../../store/user/actions';
import { userDataSelector, userIdSelector } from '../../store/user/selectors';


const ClinicSearch = ({ match }) => {
    const dispatch = useDispatch();
    const id = useSelector(userIdSelector);
    const data = useSelector(userDataSelector);
    const pageCount = useSelector(pageCountSelector);
    const [today, setToday] = useState(moment().format('YYYY-MM-DD'));
    const [date, setDate] = useState(null);
    const [type, setType] = useState(null);
    const [rating, setRating] = useState(null);   
    const [pageCnt, setPageCnt] = useState(0);
    const [filterTerm, setFilterTerm] = useState('');
    const [sort, setSort] = useState(0);
    /*const [dateString, setDateString] = useStateWithCallback(moment().format('YYYY-MM-DD'), sdString => {
        setDate((new Date(sdString)).getTime() / 1000 | 0);
    });*/

    const handleClinicSearch = (event) => {
        dispatch(
            searchClinic({
                patientId: data.patientId,
                date,
                type,
                rating,
                pageCnt,
                sort

            })
        );
    };

    useEffect(() => {
        if(date!=null && type!=null && rating!=null){
            dispatch(
                searchClinic({
                    patientId: data.patientId,
                    date,
                    type,
                    rating,
                    pageCnt,
                    sort

                })
            );
        }

    }, [pageCnt, sort]);

    let items = [];
    for (let number = 1; number <= pageCount; number++) {
        items.push(
            <Pagination.Item key={number} active={number == (pageCnt + 1)}>
                {number}
            </Pagination.Item>
        );
    }

    const handlePagination = (e) => {
        e.preventDefault();
        let event = e.target.text;
        if (event != undefined && pageCount > 0) {
            if (event.includes('First')) {
                setPageCnt(0);
            } else if (event.includes('Last')) {
                setPageCnt(pageCount - 1);
            } else if (event.includes('Next')) {
                if (pageCnt < pageCount - 1) {
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
            <Row>
                <Col md={{ span: 10, offset: 1 }} xs={12}>
                    <h2 className="border-bottom">Clinics List</h2>
                </Col>
            </Row>
            <Row>
                <Col md={{ span: 10, offset: 1 }} xs={12}>
                    <Form>
                    <Form.Row>
                        <Form.Group as={Col}>
                            <Form.Label>Date:</Form.Label>
                            <Form.Control type="date" min={today} value={date} id="date1"
                                onChange={({ currentTarget }) => {
                                    setDate(currentTarget.value);
                                }} />
                            </Form.Group>
                            <Form.Group as={Col}>
                            <Form.Label>Type:</Form.Label>
                            <Form.Control as="select" onChange={({ currentTarget }) => {
                                setType(currentTarget.value);
                            }} >
                                <option>Choose...</option>
                                <option>Tip1</option>
                                <option>Tip2</option>
                            </Form.Control>
                            </Form.Group>
                            <Form.Group as ={Col}>
                                <Form.Label>Rating:</Form.Label>
                                <Form.Control type="text" placeholder="Rating"
                                    onChange={({ currentTarget }) => {
                                    setRating(currentTarget.value);
                                }}/>
                            </Form.Group>
                            </Form.Row>
                            <Button variant="primary" className="mt-2" onClick={handleClinicSearch}>
                                Search
                            </Button>

                        </Form>
                    </Col>
                </Row>
                <Row>
                    <Col md={{ span: 3, offset: 1 }} xs={12}>
                        <Form>
                            <Form.Label>Filter clinics by</Form.Label>

                            <Form.Control as="select" onChange={({ currentTarget }) => {
                            setFilterTerm(currentTarget.value);
                        }} >
                                <option>Choose...</option>
                                <option>Price</option>
                                <option>Rating</option>
                            </Form.Control>
                        </Form>
                    </Col>
                </Row>
                <Row>
                    <Col md={{ span: 3, offset: 1 }} xs={12}>
                        <Form>
                            <Form.Label>Sort clinics by</Form.Label>
                            <Form.Control as="select" onChange={({ currentTarget }) => {
                                    setSort(currentTarget.value);
                                }} >
                                    <option value="0">-</option>
                                    <option value="1">Name (asc)</option>
                                    <option value="2">Name (desc)</option>
                                    <option value="3">City (asc)</option>
                                    <option value="4">City (desc)</option>
                                </Form.Control>
                    </Form>
                </Col>
            </Row>
            <ClinicList cnt={pageCnt} filterTerm={filterTerm} />
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

export default ClinicSearch;