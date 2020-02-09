import React, { useState, useEffect } from 'react';
import { Container, Row, Col, Form, Button, Pagination } from 'react-bootstrap';
import { useDispatch, useSelector } from 'react-redux';
import { fetchDiagnoseData } from '../../store/medicine_diagnose/actions';
import { pageSelCntSelector, pageCntSelector } from '../../store/common/selectors';
import DiagnoseTable from './DiagnoseTable';


const PaginationDiagnose = () => {
    const dispatch = useDispatch();
    const diagnosePageCnt = useSelector(pageCntSelector);
    const selPageCnt = useSelector(pageSelCntSelector);
    const [pageCnt, setPageCnt] = useState(selPageCnt);

    useEffect(() => {
        dispatch(
            fetchDiagnoseData({
                pageCnt
            })
        );
    }, [pageCnt]);

    let items = [];
    for (let number = 1; number <= diagnosePageCnt; number++) {
        items.push(
            <Pagination.Item key={number} active={number == (pageCnt + 1)}>
                {number}
            </Pagination.Item>
        );
    }

    const handlePagination = (e) => {
        e.preventDefault();
        let event = e.target.text;
        if (event != undefined && diagnosePageCnt > 0) {
            if (event.includes('First')) {
                setPageCnt(0);
            } else if (event.includes('Last')) {
                setPageCnt(diagnosePageCnt - 1);
            } else if (event.includes('Next')) {
                if (pageCnt < diagnosePageCnt - 1) {
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
            <DiagnoseTable />
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

export default PaginationDiagnose;