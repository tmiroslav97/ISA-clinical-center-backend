import React, { useState } from 'react';
import { useDispatch, useSelector } from 'react-redux';
import { Table, Button, Modal, Form, Spinner, Col } from 'react-bootstrap';
import { approveRegReq, rejectRegReq } from '../../store/reg_req/actions';
import { isFetchRegReqs, regReqsDataSelector } from '../../store/reg_req/selectors';


const RegTable = () => {
    const dispatch = useDispatch();
    const [show, setShow] = useState(false);
    const [reqId, setReqId] = useState(0);
    const [message, setMessage] = useState('');
    const regReqs = useSelector(regReqsDataSelector);
    const isFetchReqs = useSelector(isFetchRegReqs);
    const [validated, setValidated] = useState(false);

    const handleApprove = (regReqId) => {
        dispatch(
            approveRegReq({
                regReqId
            })
        );
    };

    const handleReject = (event) => {
        const form = event.currentTarget;
        event.preventDefault();
        if (form.checkValidity() === false) {
            setValidated(true);
            event.stopPropagation();
        } else {
            dispatch(
                rejectRegReq({
                    reqId,
                    message
                })
            );
            handleClose();
            setValidated(false);
        }
    };

    const handleClose = () => setShow(false);
    const handleShow = (regReqId) => {
        setShow(true);
        setReqId(regReqId);
    };

    if (!isFetchReqs) {
        return <div className="d-flex justify-content-center">
            <Spinner animation="border" role="status">
                <span className="sr-only">Loading...</span>
            </Spinner>
        </div>;
    }

    return (
        <div>
            <Modal show={show} onHide={handleClose} id="regReqModal" animation={true}>
                <Modal.Header closeButton>
                    <Modal.Title>Rejected reason:</Modal.Title>
                </Modal.Header>
                <Modal.Body>
                    <Form noValidate validated={validated} id="formRegReqReject" onSubmit={handleReject}>
                        <Form.Group as={Col}>
                            <Form.Control required as="textarea" max="90" rows="4" id="txtReason" name="reason"
                                onChange={({ currentTarget }) => {
                                    setMessage(currentTarget.value);
                                }}
                            />
                        </Form.Group>
                        <Form.Group as={Col} align="right">
                            <Button variant="primary" id="btnReject" type="submit">
                                Send
                         </Button>
                        </Form.Group>
                    </Form>
                </Modal.Body>
            </Modal>
            <Table id="regReqTable" responsive>
                <thead>
                    <tr>
                        <th>E-mail</th>
                        <th>First Name</th>
                        <th>Last Name</th>
                        <th>Approve</th>
                        <th>Refuse</th>
                    </tr>
                </thead>
                <tbody>

                    {

                        regReqs.map((req, index) => {
                            return (
                                <tr key={req.id}>
                                    <td>{req.email}</td>
                                    <td>{req.firstName}</td>
                                    <td>{req.lastName}</td>
                                    <td>
                                        <Button variant="success" onClick={() => handleApprove(req.id)}>
                                            Approve
                                            </Button>
                                    </td>
                                    <td>
                                        <Button variant="danger" type="primary" onClick={() => handleShow(req.id)}>
                                            Refuse
                                                </Button>
                                    </td>
                                </tr>
                            );
                        })

                    }
                </tbody>
            </Table>
        </div>
    );
}

export default RegTable;