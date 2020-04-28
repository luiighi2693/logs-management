import React, { useEffect } from 'react';
import { connect } from 'react-redux';
import { RouteComponentProps } from 'react-router-dom';
import { Modal, ModalHeader, ModalBody, ModalFooter, Button } from 'reactstrap';
import { ICrudGetAction, ICrudDeleteAction } from 'react-jhipster';
import { FontAwesomeIcon } from '@fortawesome/react-fontawesome';

import { ILog } from 'app/shared/model/log.model';
import { IRootState } from 'app/shared/reducers';
import { getEntity, deleteEntity } from './log.reducer';

export interface ILogDeleteDialogProps extends StateProps, DispatchProps, RouteComponentProps<{ id: string }> {}

export const LogDeleteDialog = (props: ILogDeleteDialogProps) => {
  useEffect(() => {
    props.getEntity(props.match.params.id);
  }, []);

  const handleClose = () => {
    props.history.push('/log' + props.location.search);
  };

  useEffect(() => {
    if (props.updateSuccess) {
      handleClose();
    }
  }, [props.updateSuccess]);

  const confirmDelete = () => {
    props.deleteEntity(props.logEntity.id);
  };

  const { logEntity } = props;
  return (
    <Modal isOpen toggle={handleClose}>
      <ModalHeader toggle={handleClose}>Confirm delete operation</ModalHeader>
      <ModalBody id="logSystemApp.log.delete.question">Are you sure you want to delete this Log?</ModalBody>
      <ModalFooter>
        <Button color="secondary" onClick={handleClose}>
          <FontAwesomeIcon icon="ban" />
          &nbsp; Cancel
        </Button>
        <Button id="jhi-confirm-delete-log" color="danger" onClick={confirmDelete}>
          <FontAwesomeIcon icon="trash" />
          &nbsp; Delete
        </Button>
      </ModalFooter>
    </Modal>
  );
};

const mapStateToProps = ({ log }: IRootState) => ({
  logEntity: log.entity,
  updateSuccess: log.updateSuccess
});

const mapDispatchToProps = { getEntity, deleteEntity };

type StateProps = ReturnType<typeof mapStateToProps>;
type DispatchProps = typeof mapDispatchToProps;

export default connect(mapStateToProps, mapDispatchToProps)(LogDeleteDialog);
