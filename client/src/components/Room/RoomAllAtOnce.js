import React, { useState, useEffect } from 'react';
import { Container, Row, Table, Button, Col, Form, Modal, Spinner, Pagination, PageItem } from 'react-bootstrap';
import { useDispatch, useSelector } from 'react-redux';
import { roomsDataSelector, isFetchRoomsSelector, pageCountSelector } from '../../store/rooms/selectors'
import { fetchRoomsData, searchRooms,addRoom, editRoom ,deleteRoom} from '../../store/rooms/actions';

const RoomAllAtOnce = ({ match }) => {
    const dispatch = useDispatch();
    const clinicId = match.params.clinicId;
    const rooms = useSelector(roomsDataSelector);
    const isFetchRoomsData = useSelector(isFetchRoomsSelector);
    const pageCount = useSelector(pageCountSelector);
    const [pageCnt, setPageCnt] = useState(0);
    const [name, setName] = useState('');
    const [roomNum, setRoomNum] = useState(0);
    const [type, setType] = useState('');
    const [roomId, setRoomId] = useState(0);

    const handleDelitingRooms = (room) => {
        dispatch(
            deleteRoom({id:room.id, clinicId, pageCnt})
        );
    };

    const handleSearchRooms = () => {
        dispatch(
            searchRooms({name, clinicId, pageCnt})
        );
    }

    const handelAddRoom = () => {
        dispatch(
            addRoom({roomId,name,roomNum, type, clinicId, pageCnt})
        );
        setShow2rAdd(false);
    }
    const handleEditShow  = (room)=>{
        setName(room.name);
        setRoomNum(room.roomNum);
        setRoomId(room.id);
        setShow1rEdit(true);
    }

    const handleEdit = () => {
        dispatch(
            editRoom({id:roomId, name, roomNum, clinicId, pageCnt})
        )
        setShow1rEdit(false);
    }

    useEffect(() => {
        dispatch(
            fetchRoomsData({
                clinicId,
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
            }else{
                setPageCnt(event-1);
            }
        }
    };


    const [show1rEdit, setShow1rEdit] = useState(false);
    const [show2rAdd, setShow2rAdd] = useState(false);

    const handleClose1rEdit = () => setShow1rEdit(false);
    const handleShow1rEdit = () => setShow1rEdit(true);
    const handleShow2rAdd = () => setShow2rAdd(true);

    if (!isFetchRoomsData) {
        return <div className="d-flex justify-content-center">
            <Spinner animation="border" role="status">
                <span className="sr-only">Loading...</span>
            </Spinner>
        </div>;
    }

    return (
        <>
            <Modal show={show1rEdit} onHide={handleEdit} animation={false}>
                <Modal.Header closeButton>
                    <Modal.Title>Edit data:</Modal.Title>
                </Modal.Header>
                <Modal.Body>
                    <Form>
                        <Form.Group as={Row}>
                            <Form.Label>Enter name of the room:</Form.Label>
                            <Col>
                                <Form.Control type="text" value = {name}  placeholder="Name " onChange={({ currentTarget }) => {
                                setName(currentTarget.value);}} />
                            </Col>
                        </Form.Group>
                        <Form.Group as={Row}>
                            <Form.Label>Enter number of the room:</Form.Label>
                            <Col>
                                <Form.Control type="number" value = {roomNum} placeholder="Number " onChange={({ currentTarget }) => {
                                setRoomNum(currentTarget.value);}}/>
                            </Col>
                        </Form.Group>
                    </Form>
                </Modal.Body>
                <Modal.Footer>
                    <Button variant="primary" onClick={handleEdit}>
                        Edit
          </Button>
                </Modal.Footer>
            </Modal>

            <Modal show={show2rAdd} onHide={handelAddRoom} animation={false}>
                <Modal.Header closeButton>
                    <Modal.Title>Add data:</Modal.Title>
                </Modal.Header>
                <Modal.Body>
                    <Form>
                        <Form.Group as={Row}>
                            <Form.Label>Enter name of the room:</Form.Label>
                            <Col>
                                <Form.Control type="text" placeholder="Name " onChange={({ currentTarget }) => {
                                setName(currentTarget.value);}} />
                            </Col>
                        </Form.Group>
                        <Form.Group as={Row}>
                            <Form.Label>Enter number of the room:</Form.Label>
                            <Col>
                                <Form.Control type="number" placeholder="Number "  onChange={({ currentTarget }) => {
                                setRoomNum(currentTarget.value);}}/>
                            </Col>
                        </Form.Group>
                        <Form.Group as={Row}>
                            <Form.Label>Choose type:</Form.Label>
                            <Col>
                            <Form.Control as="select" onChange={({ currentTarget }) => {
                            setType(currentTarget.value);
                        }} >
                            <option value=""></option>
                            <option value="SUR">surgery</option>
                            <option value="APP">exemination</option>
                        </Form.Control>
                            </Col>
                        </Form.Group>
                    </Form>
                </Modal.Body>
                <Modal.Footer>
                    <Button variant="primary" onClick={handelAddRoom}>
                        Add
          </Button>
                </Modal.Footer>
            </Modal>
            <Container>
                <Row>
                    <Col md={{ span: 10, offset: 1 }} xs={12}>
                        <h3 className="border-bottom">Examination and surgery rooms</h3>
                    </Col>
                </Row>
                <Row>
                    <Col md={{ span: 10, offset: 1 }} xs={12}>
                        <Form>

                            <Form.Group as={Row} >

                                <Form.Label>Add new surgery room:</Form.Label>
                                <Col>
                                    <Button onClick={handleShow2rAdd}>Add </Button>
                                </Col>
                            </Form.Group>

                            <Form.Group as={Row} >

                                <Form.Label>Search surgery rooms by name:</Form.Label>
                                <Col>
                                    <Form.Control type="text" placeholder="Search " onChange={({ currentTarget }) => {
                                setName(currentTarget.value);}} />
                                </Col>
                                <Col>
                                    <Button onClick={handleSearchRooms}>Search</Button>
                                </Col>
                            </Form.Group>

                        </Form>
                    </Col>
                </Row>
                <Row>
                    <Col md={{ span: 10, offset: 1 }} xs={12}>
                        <Table responsive>
                            <thead>
                                <tr>
                                    <th>#</th>
                                    <th>Name</th>
                                    <th>Number</th>
                                    <th>Edit</th>
                                    <th>Delete</th>
                                </tr>
                            </thead>
                            <tbody>
                                {
                                    rooms.map((room, index) => {
                                        return (
                                            <tr key={room.id}>
                                                <td>{index+1}</td>
                                                <td>{room.name}</td>
                                                <td>{room.roomNum}</td>
                                                <td>
                                                    <Button variant="success" onClick={()=>{handleEditShow(room)}}>Edit</Button>
                                                </td>
                                                <td>
                                                    <Button  variant="danger" onClick={() => {handleDelitingRooms(room)}}>Delete</Button>
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
            </Container >
        </>
    );
}


export default RoomAllAtOnce;