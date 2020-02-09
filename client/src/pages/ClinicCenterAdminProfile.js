import React, { useEffect } from 'react';
import { history } from '../index';
import { useDispatch, useSelector } from 'react-redux';
import { Card, Button, Col, Row, Container, Spinner } from 'react-bootstrap';
import { fetchCCAdminData } from '../store/user/actions';
import { userIdSelector, userDataSelector, isFetchUserDataSelector } from '../store/user/selectors';


const ClinicCenterAdminProfile = () => {
    const dispatch = useDispatch();
    const ccAdminId = useSelector(userIdSelector);
    const isFetchUserData = useSelector(isFetchUserDataSelector);
    const userData = useSelector(userDataSelector);

    useEffect(() => {
        if (ccAdminId != null) {
            dispatch(
                fetchCCAdminData({
                    ccAdminId
                })
            );
        }
    }, [ccAdminId]);

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
                    <h2 className="border-bottom">Clinic center admin profile</h2>
                </Col>
            </Row>
            <Row>
                <Col md={{ span: 3, offset: 1 }} xs={12}>
                    <Card style={{ width: '18rem' }}>
                        <Card.Body>
                            <Card.Title>Registration reqs</Card.Title>
                            <Card.Text>
                                You can see registration requirements.
                            </Card.Text>
                            <Button variant="primary" id="btnRegReqs" onClick={() => { history.push('/ccadmin/reg-req'); }}>Reqs</Button>
                        </Card.Body>
                    </Card>
                </Col>
                <Col md={{ span: 3 }} xs={12}>
                    <Card style={{ width: '18rem' }}>
                        <Card.Body>
                            <Card.Title>Clinic reg</Card.Title>
                            <Card.Text>
                                You can registrate new clinic.
                            </Card.Text>
                            <Button variant="primary" onClick={() => { history.push('/ccadmin/cli-reg'); }}>Clinic</Button>
                        </Card.Body>
                    </Card>
                </Col>
                <Col md={{ span: 4 }} xs={12}>
                    <Card style={{ width: '18rem' }}>
                        <Card.Body>
                            <Card.Title>Clinic admin reg</Card.Title>
                            <Card.Text>
                                You can registrate clinic admin.
                            </Card.Text>
                            <Button variant="primary" onClick={() => { history.push('/ccadmin/regca'); }}>Go to</Button>
                        </Card.Body>
                    </Card>
                </Col>
            </Row>
            <Row>
                <Col md={{ span: 3, offset: 1 }} xs={12}>
                    <Card style={{ width: '18rem' }}>
                        <Card.Body>
                            <Card.Title>Add medicine</Card.Title>
                            <Card.Text>
                                You can add medicine.
                            </Card.Text>
                            <Button variant="primary" onClick={() => { history.push('/ccadmin/add-med'); }} >Add</Button>
                        </Card.Body>
                    </Card>
                </Col>
                <Col md={{ span: 3 }} xs={12}>
                    <Card style={{ width: '18rem' }}>
                        <Card.Body>
                            <Card.Title>Add diagnose</Card.Title>
                            <Card.Text>
                                You can add diagnose.
                            </Card.Text>
                            <Button variant="primary" onClick={() => { history.push('/ccadmin/add-diag'); }}>Add</Button>
                        </Card.Body>
                    </Card>
                </Col>
                {userData.predefined &&
                    <Col md={{ span: 3 }} xs={12}>
                        <Card style={{ width: '18rem' }}>
                            <Card.Body>
                                <Card.Title>Add clinic center admin</Card.Title>
                                <Card.Text>
                                    You can add clinic center admin.
                            </Card.Text>
                                <Button variant="primary" onClick={() => { history.push('/ccadmin/cca-reg'); }}>Add</Button>
                            </Card.Body>
                        </Card>
                    </Col>
                }
            </Row>
        </Container>
    );
}

export default ClinicCenterAdminProfile;