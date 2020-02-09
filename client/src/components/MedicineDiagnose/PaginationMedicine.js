import React, { useState, useEffect } from 'react';
import { Container, Row, Col, Pagination } from 'react-bootstrap';
import { useDispatch, useSelector } from 'react-redux';
import { fetchMedicineData } from '../../store/medicine_diagnose/actions';
import { pageCntSelector, pageSelCntSelector } from '../../store/common/selectors';
import MedicineTable from './MedicineTable';


const PaginationMedicine = () => {
    const dispatch = useDispatch();
    const medicinePageCnt = useSelector(pageCntSelector);
    const selPageCnt = useSelector(pageSelCntSelector);
    const [pageCnt, setPageCnt] = useState(selPageCnt);
    useEffect(() => {
        dispatch(
            fetchMedicineData({
                pageCnt
            })
        );
    }, [pageCnt]);

    let items = [];
    for (let number = 1; number <= medicinePageCnt; number++) {
        items.push(
            <Pagination.Item key={number} active={number == (selPageCnt + 1)}>
                {number}
            </Pagination.Item>
        );
    }

    const handlePagination = (e) => {
        e.preventDefault();
        let event = e.target.text;
        if (event != undefined && medicinePageCnt > 0) {
            if (event.includes('First')) {
                setPageCnt(0);
            } else if (event.includes('Last')) {
                setPageCnt(medicinePageCnt - 1);
            } else if (event.includes('Next')) {
                if (pageCnt < medicinePageCnt - 1) {
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
            <MedicineTable />
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

export default PaginationMedicine;