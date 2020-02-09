import React, { useState, useEffect } from 'react';
import { Container, Row, Table, Button, Col, Spinner, Pagination } from 'react-bootstrap';
import { useDispatch, useSelector } from 'react-redux';
import { surReqDataSelector, isFetchSurReqDataSelector, surReqPageCountSelector } from '../../store/sur_req/selectors';
import { fetchSurReqData, fetchPickSurRoom } from '../../store/sur_req/actions';

const SurgeryReq = ({ match }) => {
    const dispatch = useDispatch();
    const clinicId = match.params.clinicId;
    const [pageCnt, setPageCnt] = useState(0);
    const isFetchSurReqData = useSelector(isFetchSurReqDataSelector);
    const surReqs = useSelector(surReqDataSelector);
    const pageCount = useSelector(surReqPageCountSelector);

    useEffect(() => {
        dispatch(
            fetchSurReqData({
                clinicId,
                pageCnt
            })
        );
    }, [pageCnt]);

    const handlePickRoom = (pickedSurReq) => {
        dispatch(
            fetchPickSurRoom({
                pickedSurReq
            })
        );
    }

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
        if (event != undefined) {
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

    if (!isFetchSurReqData) {
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
                    <h3 className="border-bottom">Surgery requirements</h3>
                </Col>
            </Row>
            <Row>
                <Col md={{ span: 10, offset: 1 }} xs={12}>
                    <Table responsive>
                        <thead>
                            <tr>
                                <th>#</th>
                                <th>Patient</th>
                                <th>Doctor</th>
                                <th>Required surgery date</th>
                                <th>Term</th>
                                <th>Action</th>
                            </tr>
                        </thead>
                        <tbody>
                            {
                                surReqs.map((surReq, index) => {
                                    return (
                                        <tr key={surReq.id}>
                                            <td>{index + 1}</td>
                                            <td>{surReq.patientName}</td>
                                            <td>{surReq.doctorName}</td>
                                            <td>{surReq.date}</td>
                                            <td>{surReq.termin}-{surReq.termin + 3}</td>
                                            <td>
                                                <Button onClick={(e) => handlePickRoom(surReq)}>Reserve this</Button>
                                            </td>
                                        </tr>
                                    );
                                })
                            }
                        </tbody>
                    </Table>
                </Col>
            </Row>
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

export default SurgeryReq;