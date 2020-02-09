import React, { useEffect } from 'react';
import { useDispatch, useSelector } from 'react-redux';
import { Spinner, Card, Button, Col, Row, Container } from 'react-bootstrap';
import { userDataSelector, userIdSelector, isFetchUserDataSelector } from '../../store/user/selectors';
import { fetchCAdminData } from '../../store/user/actions';
import { history } from '../../index';


const CA = () => {
    const dispatch = useDispatch();
    const id = useSelector(userIdSelector);
    const data = useSelector(userDataSelector);
    const isFetchUserData = useSelector(isFetchUserDataSelector);

    useEffect(() => {
        if (id != null) {
            dispatch(
                fetchCAdminData({
                    id
                })
            );
        }
    }, [id]);

    if (!isFetchUserData) {
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
                    <h2 className="border-bottom">Clinic admin profile</h2>
                </Col>
            </Row>
            <Row>
                <Col md={{ span: 3, offset: 1 }} xs={12}>
                    <Card style={{ width: '18rem' }}>
                        <Card.Body>
                            <Card.Title>Clinic doctors</Card.Title>
                            <Card.Text>
                                You can acces doctors.
                            </Card.Text>
                            <Button variant="primary" onClick={() => { history.push('/adminc/doc/' + data.clinicId); }}>Doctors</Button>
                        </Card.Body>
                    </Card>
                </Col>
                <Col md={{ span: 3 }} xs={12}>
                    <Card style={{ width: '18rem' }}>
                        <Card.Body>
                            <Card.Title>Clinic profile</Card.Title>
                            <Card.Text>
                                You can acces clinic profile
                            </Card.Text>
                            <Button variant="primary" onClick={() => { history.push('/adminc/cli-prof/' + data.clinicId); }}>Clinic profile</Button>
                        </Card.Body>
                    </Card>
                </Col>
                <Col md={{ span: 4 }} xs={12}>
                    <Card style={{ width: '18rem' }}>
                        <Card.Body>
                            <Card.Title>Appointment type</Card.Title>
                            <Card.Text>
                                You can access appointment types.
                            </Card.Text>
                            <Button variant="primary" onClick={() => { history.push('/adminc/app-type'); }}>Type</Button>
                        </Card.Body>
                    </Card>
                </Col>
            </Row>
            <Row>
                <Col md={{ span: 3, offset: 1 }} xs={12}>
                    <Card style={{ width: '18rem' }}>
                        <Card.Body>
                            <Card.Title>Exemination room</Card.Title>
                            <Card.Text>
                                You can add, edit, search and delete exemination room.
                            </Card.Text>
                            <Button variant="primary" onClick={() => { history.push('/adminc/exe-room/' + data.clinicId); }} >Rooms</Button>
                        </Card.Body>
                    </Card>
                </Col>
                <Col md={{ span: 3 }} xs={12}>
                    <Card style={{ width: '18rem' }}>
                        <Card.Body>
                            <Card.Title>Room search</Card.Title>
                            <Card.Text>
                                You can easily search rooms.
                            </Card.Text>
                            <Button variant="primary" onClick={() => { history.push('/adminc/room-search/free'); }}>Search rooms</Button>
                        </Card.Body>
                    </Card>
                </Col>
                <Col md={{ span: 4 }} xs={12}>
                    <Card style={{ width: '18rem' }}>
                        <Card.Body>
                            <Card.Title>Free appointments</Card.Title>
                            <Card.Text>
                                You can easily create free apointmets.
                            </Card.Text>
                            <Button variant="primary" onClick={() => { history.push('/adminc/free-app/' + data.clinicId); }}>Appointments</Button>
                        </Card.Body>
                    </Card>
                </Col>
            </Row>
            <Row>
                <Col md={{ span: 3, offset: 1 }} xs={12}>
                    <Card style={{ width: '18rem' }}>
                        <Card.Body>
                            <Card.Title>Price list</Card.Title>
                            <Card.Text>
                                You can acces price list.
                            </Card.Text>
                            <Button variant="primary" onClick={() => { history.push('/adminc/price-list/' + data.clinicId); }}>List</Button>
                        </Card.Body>
                    </Card>
                </Col>
                <Col md={{ span: 3 }} xs={12}>
                    <Card style={{ width: '18rem' }}>
                        <Card.Body>
                            <Card.Title>Business report</Card.Title>
                            <Card.Text>
                                You can access business report.
                            </Card.Text>
                            <Button variant="primary" onClick={() => { history.push('/adminc/bus-rep/' + data.clinicId); }}>Business</Button>
                        </Card.Body>
                    </Card>
                </Col>
                <Col md={{ span: 4 }} xs={12}>
                    <Card style={{ width: '18rem' }}>
                        <Card.Body>
                            <Card.Title>User profile</Card.Title>
                            <Card.Text>
                                You can easily acces yours profile
                            </Card.Text>
                            <Button variant="primary" onClick={() => { history.push('/user-prof'); }}>Profile</Button>
                        </Card.Body>
                    </Card>
                </Col>
            </Row>
            <Row>
                <Col md={{ span: 3, offset: 1 }} xs={12}>
                    <Card style={{ width: '18rem' }}>
                        <Card.Body>
                            <Card.Title>Surgery requirement</Card.Title>
                            <Card.Text>
                                You can surgery requirements.
                            </Card.Text>
                            <Button variant="primary" onClick={() => { history.push('/adminc/sur-req/' + data.clinicId); }}>Requirements</Button>
                        </Card.Body>
                    </Card>
                </Col>
            </Row>
        </Container>
    );

}

export default CA;