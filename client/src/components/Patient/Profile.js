import React from 'react';
import { Row, Tab, Nav, Col } from 'react-bootstrap';
import PasswordChanger from '../PasswordChanger';
import UpdateProfile from '../UpdateProfile';

function Profile(){
    return(
        <Tab.Container id="left-tabs" defaultActiveKey="first">
            <Row>
                <Col md={2} xs={12}>
                    <Nav variant="pills" className="flex-column">
                        <Nav.Item>
                            <Nav.Link eventKey="first">Update the profile</Nav.Link>
                        </Nav.Item>
                        <Nav.Item>
                            <Nav.Link eventKey="second">Change the password</Nav.Link>
                        </Nav.Item>
                    </Nav>
                </Col>
                <Col md={10} xs={12}>
                    <Tab.Content>
                        <Tab.Pane eventKey="first">
                            <UpdateProfile/>
                        </Tab.Pane>
                        <Tab.Pane eventKey="second">
                            <PasswordChanger/>
                        </Tab.Pane>
                    </Tab.Content>
                </Col>
            </Row>
        </Tab.Container>

    );


}
export default Profile;