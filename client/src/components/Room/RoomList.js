import React, { useState } from 'react';
import { Container, Row, Col, Spinner, Table, Form, Button } from 'react-bootstrap';
import { useSelector, useDispatch } from 'react-redux';
import { roomsDataSelector, isFetchRoomsSelector } from '../../store/rooms/selectors';
import { pickSurReqSelector } from '../../store/sur_req/selectors';
import { fetchPickDoc } from '../../store/sur_req/actions';


const RoomList = ({ filterTerm, cnt, reason }) => {
    const dispatch = useDispatch();
    const rooms = useSelector(roomsDataSelector);
    const isFetchRoomsData = useSelector(isFetchRoomsSelector);
    const pickSurReq = useSelector(pickSurReqSelector);
    const [pickedTerm, setPickedTerm] = useState('');
    const [preTermins, setPreTermins] = useState([7, 10, 13, 16]);
    const [preTerminsApp, setPreTerminsApp] = useState([7, 8, 9, 10, 11, 12, 13, 14, 15, 16, 17, 18]);
    const [validated, setValidated] = useState(false);

    const handleTerm = (pickedRoom) => {
        dispatch(
            fetchPickDoc({
                pickedRoom,
                pickedTerm
            })
        );
    };

    const handleFirst = (pickedRoom, pickedTerm) => {
        dispatch(
            fetchPickDoc({
                pickedRoom,
                pickedTerm
            })
        );
    };

    if (!isFetchRoomsData) {
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
                    <h3>Rooms list</h3>
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
                                <th>Free terms</th>
                                <th>First free</th>
                            </tr>
                        </thead>
                        <tbody>
                            {
                                rooms.filter(roomsDto => roomsDto.room.type.includes(filterTerm)).map((roomsDto, index) => {
                                    return (
                                        <tr key={roomsDto.room.id}>
                                            <td>{cnt * 10 + index + 1}</td>
                                            <td>{roomsDto.room.name}</td>
                                            <td>{roomsDto.room.roomNum}</td>
                                            <td>
                                                <Form noValidate onSubmit={(event) => {
                                                    const form = event.currentTarget;
                                                    event.preventDefault();
                                                    if (form.checkValidity() === false) {
                                                        event.stopPropagation();
                                                        alert('You must pick term');
                                                        form.validate = true;
                                                    } else {
                                                        handleTerm(roomsDto.room.id);
                                                        form.validate = false;
                                                    }
                                                }}>
                                                    <Form.Group as={Col}>
                                                        <Form.Control required as="select" id={roomsDto.room.id} defaultValue={"none"} onChange={({ currentTarget }) => {
                                                            setPickedTerm(currentTarget.value);
                                                        }} >
                                                            <option></option>
                                                            {
                                                                roomsDto.room.type === 'SUR' ?
                                                                    preTermins.map((termin) => {
                                                                        let flag = roomsDto.termins.includes(termin);
                                                                        return (
                                                                            <option key={roomsDto.room.id * 10 + termin} disabled={flag} value={roomsDto.date + " " + termin + "-" + (termin + 3)}>{termin}-{termin + 3}</option>
                                                                        );
                                                                    }) :
                                                                    preTerminsApp.map((termin) => {
                                                                        let flag = roomsDto.termins.includes(termin);
                                                                        return (
                                                                            <option key={roomsDto.room.id * 10 + termin} disabled={flag} value={roomsDto.date + " " + termin + "-" + (termin + 1)}>{termin}-{termin + 1}</option>
                                                                        );
                                                                    })
                                                            }
                                                        </Form.Control>
                                                        {
                                                            pickSurReq && roomsDto.room.type === reason ? <Button className="mt-2" id={'btn'+roomsDto.room.id} type="submit">Reserve</Button> : null
                                                        }
                                                    </Form.Group>

                                                </Form>
                                            </td>
                                            <td>{roomsDto.firstFreeTermin}<br />
                                                {
                                                    pickSurReq && roomsDto.room.type === reason ? <Button id={'btnFast'+roomsDto.room.id} onClick={(e) => { handleFirst(roomsDto.room.id, roomsDto.firstFreeTermin) }}>Reserve</Button> : null
                                                }
                                            </td>
                                        </tr>
                                    );
                                })
                            }
                        </tbody>
                    </Table>
                </Col>
            </Row>
        </Container >
    );
}

export default RoomList;