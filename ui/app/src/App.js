import React, {Component} from 'react';
import './App.css';
import {Button, Container, Modal, ModalBody, ModalFooter, ModalHeader} from "reactstrap";

class App extends Component {
    state = {
        isLoading: true,
        transactions: [],
        modal: false,
        selectedTransactionId: null
    };

    async componentDidMount() {
        const response = await fetch('/transactions');
        const body = await response.json();
        this.setState({ transactions: body, isLoading: false });
    }

    toggle = (transactionId) => {
        this.setState({
            modal: !this.state.modal,
            selectedTransactionId: transactionId
        });
    };

    getTransactionAmount = (transaction) => {
        return transaction.type === 'DEBIT' ? -transaction.amount : transaction.amount;
    };

    getModal = () => {
        if (!this.state.modal) {
            return;
        }

        const selectedTransaction = this.state.transactions.find(transaction => transaction.id === this.state.selectedTransactionId);
        return (
            <Modal isOpen={this.state.modal} toggle={this.toggle}>
                <ModalHeader toggle={this.toggle}>Transaction</ModalHeader>
                <ModalBody>
                    Type: {selectedTransaction.type}<br/>
                    Amount: {this.getTransactionAmount(selectedTransaction)}<br/>
                    Date: {selectedTransaction.effectiveDate}
                </ModalBody>
                <ModalFooter>
                    <Button color="primary" onClick={this.toggle}>Close</Button>{' '}
                </ModalFooter>
            </Modal>
        );
    };

    render() {
        const {transactions, isLoading} = this.state;

        if (isLoading) {
            return <p>Loading...</p>;
        }

        return (
            <div className="App">
                <h2>Transactions</h2>
                <Container fluid>
                    <table className="table table-bordered">
                        <thead className="thead-light">
                        <tr>
                            <th>Type</th>
                            <th>Amount</th>
                        </tr>
                        </thead>
                        <tbody>
                            {transactions.map(transaction =>
                                <tr key={transaction.id} data-id={transaction.id} className={transaction.type === 'DEBIT' ? 'debit-row' : ''} onClick={() => this.toggle(transaction.id)}>
                                    <td>{transaction.type}</td>
                                    <td>{this.getTransactionAmount(transaction)}</td>
                                </tr>
                            )}
                        </tbody>
                    </table>
                </Container>
                {this.getModal()}
            </div>
        );
    }
}

export default App;