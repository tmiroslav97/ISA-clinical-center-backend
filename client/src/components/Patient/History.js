import React from 'react';
import {Table, Row, Col, InputGroup, DropdownButton, Dropdown, Container, FormControl} from 'react-bootstrap';

function History(){
    return(
        <Container>
            <Row >
                <Col md={{ span:12 }} xs={12}>
                    <h3 className="border-bottom">History of surgeries and examinations</h3>
                </Col>
            </Row>
            <Row>
            <Col md={{ span:8,offset:2}} xs={12}>  
                <InputGroup>
                <FormControl
                placeholder="Sort history by"
                aria-describedby="basic-addon2"
                />
 
                <DropdownButton
                as={InputGroup.Append}
                variant="outline-secondary"
                title="Options"
                id="input-group-dropdown-2"
                >
                    <Dropdown.Item href="#">Date</Dropdown.Item>
                    <Dropdown.Item href="#">Surgeries</Dropdown.Item>
                    <Dropdown.Item href="#">Examinations</Dropdown.Item>
                </DropdownButton>
                </InputGroup>
                </Col>
                </Row>
                <Row>
                <Col md={{ span:8,offset:2}} xs={12}> 
                    <Table responsive>
                        <thead>
                            <tr>
                                <th>Date</th>
                                <th>Surgeries</th>
                                <th>Examinations</th>
                            </tr>
                        </thead>
                        <tbody>
                            <tr>
                                <td>07/25/2019</td>
                                <td>nesto</td>
                                <td>bbbb</td>
                            </tr>
                            <tr>
                                <td>10/11/2019</td>
                                <td>aaa</td>
                                <td>ww</td>
                            </tr>
                        </tbody>
                    </Table>
                    </Col>
                </Row>

        </Container>

    );


}
export default History;