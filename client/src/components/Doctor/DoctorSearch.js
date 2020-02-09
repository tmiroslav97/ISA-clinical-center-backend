import React, { useEffect, useState } from 'react';
import useStateWithCallback from 'use-state-with-callback';
import { Container, Row, Col, Table, Form, Button, Pagination } from 'react-bootstrap';
import { useDispatch, useSelector } from 'react-redux';
import moment from 'moment';
import { searchDoctor } from '../../store/doctors/actions';
import { pageCountSelector } from '../../store/doctors/selectors';
import DoctorList from './DoctorList';


const DoctorSearch = () => {
    const dispatch = useDispatch();
    const pageCount = useSelector(pageCountSelector);
    const [today, setToday] = useState(moment().format('YYYY-MM-DD'));
    const [date, setDate] = useState(moment().format('YYYY-MM-DD'));
    const [firstName, setFirstName] = useState('');
    const [lastName, setLastName] = useState('');
    const [averageRating, setAverageRating] = useState('');
    const [type, setType] = useState('');
    const [pageCnt, setPageCnt] = useState(0);
    const [filterTerm, setFilterTerm] = useState('');

    const handleDoctorsSearch = () => {
        dispatch(
            searchDoctor({
                firstName,
                lastName,
                averageRating,
                date,
                type,
                pageCnt
            })
        );

    };

    useEffect(() => {
        dispatch(
            searchDoctor({
                firstName,
                lastName,
                averageRating,
                date,
                type,
                pageCnt
            })
        );
    }, [pageCnt]);

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
                    <h2 className="border-bottom">Doctors List</h2>
                </Col>
            </Row>
            <Row>
                <Col md={{ span: 10, offset: 1 }} xs={12}>
                    <Form>
                        <Form.Row>
                        <Form.Group as={Col}>
                        <Form.Label>First name:</Form.Label>
                        <Form.Control type="text" placeholder="Enter first name"
                            onChange={({ currentTarget }) => {
                                setFirstName(currentTarget.value);
                            }} 
                        />
                        </Form.Group>
                        <Form.Group as={Col} >
                        <Form.Label>Last name:</Form.Label>
                        <Form.Control type="text" placeholder="Enter last name"
                            onChange={({ currentTarget }) => {
                                setLastName(currentTarget.value);
                            }}
                        />
                        </Form.Group>
                        <Form.Group as={Col} >
                        <Form.Label>Average rating:</Form.Label>
                        <Form.Control type="text" placeholder="Enter average rating"
                            onChange={({ currentTarget }) => {
                                setAverageRating(currentTarget.value);
                            }} 
                        />
                        </Form.Group>
                        </Form.Row>
                            <Button variant="primary" className="mt-2" onClick={handleDoctorsSearch}>
                                Search
                            </Button>
                    </Form>
                    </Col>
                    </Row>
                    <Row>
                    <Col md={{ span: 3, offset: 1 }} xs={12}>
                    <Form>
                        <Form.Label>Filter doctors by</Form.Label>
                            <Form.Control as="select">
                                <option>Choose...</option>
                                <option>...</option>
                            </Form.Control>
                    </Form>
            </Col>
        </Row>
        <DoctorList cnt={pageCnt} filterTerm={filterTerm} />
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
    )

}

export default DoctorSearch;