import React, { useEffect } from 'react';
import { useDispatch, useSelector } from 'react-redux';
import { Row, Col, Container, Table, Spinner } from 'react-bootstrap';
import { fetchAbsHolRequests } from '../../store/absence_holiday/actions';
import { absHolRequestDataSelector, isFetchAbsHolRequestsSelector } from '../../store/absence_holiday/selectors';

const HolAbsRequestsTable = ({ personnelId }) => {
    const dispatch = useDispatch();
    const absholrequests = useSelector(absHolRequestDataSelector);
    const isFetchAbsHolRequests = useSelector(isFetchAbsHolRequestsSelector);


    useEffect(() => {
        if (personnelId != null) {
            dispatch(
                fetchAbsHolRequests({
                    personnelId
                })
            );
        }
    }, [personnelId]);


    if (!isFetchAbsHolRequests) {
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
                    <h3>Pending absence and holiday requests</h3>
                </Col>
            </Row>
            <Row>
                <Col md={{ span: 10, offset: 1 }} xs={12}>
                    <Table responsive>
                        <thead>
                            <tr>
                                <th>Type</th>
                                <th>Start date</th>
                                <th>End date</th>
                            </tr>
                        </thead>
                        <tbody>
                            {
                                absholrequests.map((req, index) => {
                                    if (req.status === 'REQUESTED') {
                                        return (
                                            <tr key={index}>
                                                <td>{req.type}</td>
                                                <td>{req.startDate}</td>
                                                <td>{req.endDate}</td>
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
                    <h3>Approved absence and holiday requests</h3>
                </Col>
            </Row>
            <Row>
                <Col md={{ span: 10, offset: 1 }} xs={12}>
                    <Table responsive>
                        <thead>
                            <tr>
                                <th>Type</th>
                                <th>Start date</th>
                                <th>End date</th>
                            </tr>
                        </thead>
                        <tbody>
                            {
                                absholrequests.map((req, index) => {
                                    if (req.status === 'APPROVED') {
                                        return (
                                            <tr key={index}>
                                                <td>{req.type}</td>
                                                <td>{req.startDate}</td>
                                                <td>{req.endDate}</td>
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

export default HolAbsRequestsTable;