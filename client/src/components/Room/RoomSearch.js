import React, { useState, useEffect } from 'react';
import { Container, Row, Col, Form, Button, Pagination, Table } from 'react-bootstrap';
import { useDispatch, useSelector } from 'react-redux';
import moment from 'moment';
import { searchRoomsData } from '../../store/rooms/actions';
import { pageCountSelector } from '../../store/rooms/selectors';
import { pickSurReqSelector, pickedSurReqSelector } from '../../store/sur_req/selectors';
import { userDataSelector, userIdSelector } from '../../store/user/selectors';
import { fetchCAdminData } from '../../store/user/actions';
import RoomList from './RoomList';
import PickedSurReq from '../SurgeryRequirement/PickedSurReq';


const RoomSearch = ({ match }) => {
    const dispatch = useDispatch();
    const id = useSelector(userIdSelector);
    const reason = match.params.reason;
    const data = useSelector(userDataSelector);
    const clinicId = data.clinicId;
    const pageCount = useSelector(pageCountSelector);
    const pickSurReq = useSelector(pickSurReqSelector);
    const pickedSurReq = useSelector(pickedSurReqSelector);
    const [today, setToday] = useState(moment(new Date()).add(1,'days').format('YYYY-MM-DD'));
    const [date, setDate] = useState(moment(new Date()).add(1,'days').format('YYYY-MM-DD'));
    const [name, setName] = useState('');
    const [pageCnt, setPageCnt] = useState(0);
    const [filterTerm, setFilterTerm] = useState('');

    const handleRoomsSearch = () => {
        dispatch(
            searchRoomsData({
                name,
                date,
                clinicId,
                pageCnt
            })
        );
    };

    useEffect(() => {
        dispatch(
            searchRoomsData({
                name,
                date,
                clinicId,
                pageCnt
            })
        );
        if (id != null) {
            dispatch(
                fetchCAdminData({
                    id
                })
            );
        }
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
            {
                pickSurReq ?
                    <PickedSurReq pickedSurReq={pickedSurReq} /> : null
            }
            <Row>
                <Col md={{ span: 10, offset: 1 }} xs={12}>
                    <h2 className="border-bottom">Search rooms</h2>
                </Col>
            </Row>
            <Row>
                <Col md={{ span: 10, offset: 1 }} xs={12}>
                    <Form>
                        <Form.Row>
                            <Form.Group as={Col} >
                                <Form.Label>Room name:</Form.Label>
                                <Form.Control type="text" placeholder="Name"
                                    onChange={({ currentTarget }) => {
                                        setName(currentTarget.value);
                                    }} />
                            </Form.Group>
                            <Form.Group as={Col} >
                                <Form.Label>Date:</Form.Label>
                                <Form.Control type="date" min={today} value={date} id="date1"
                                    onChange={({ currentTarget }) => {
                                        setDate(currentTarget.value);
                                    }}
                                    onKeyDown={() => {
                                        return false;
                                    }} />
                            </Form.Group>
                        </Form.Row>
                        <Button variant="primary" className="mb-4" onClick={handleRoomsSearch}>
                            Search
                        </Button>

                    </Form>
                </Col>
            </Row>
            <Row>
                <Col md={{ span: 3, offset: 1 }} xs={12}>
                    <Form>
                        <Form.Label>Filter by:</Form.Label>
                        <Form.Control as="select" onChange={({ currentTarget }) => {
                            setFilterTerm(currentTarget.value);
                        }} >
                            <option value=""></option>
                            <option value="SUR">type: surgery</option>
                            <option value="APP">type: exemination</option>
                        </Form.Control>
                    </Form>
                </Col>
            </Row>
            <RoomList cnt={pageCnt} filterTerm={filterTerm} reason={reason} />
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

export default RoomSearch;