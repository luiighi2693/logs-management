import React, { useState, useEffect } from 'react';
import { connect } from 'react-redux';
import { Link, RouteComponentProps } from 'react-router-dom';
import { Button, Row, Col, Label } from 'reactstrap';
import { AvFeedback, AvForm, AvGroup, AvInput, AvField } from 'availity-reactstrap-validation';
import { ICrudGetAction, ICrudGetAllAction, ICrudPutAction } from 'react-jhipster';
import { FontAwesomeIcon } from '@fortawesome/react-fontawesome';
import { IRootState } from 'app/shared/reducers';

import { getEntity, updateEntity, createEntity, reset } from './log.reducer';
import { ILog } from 'app/shared/model/log.model';
import { convertDateTimeFromServer, convertDateTimeToServer, displayDefaultDateTime } from 'app/shared/util/date-utils';
import { mapIdList } from 'app/shared/util/entity-utils';

export interface ILogUpdateProps extends StateProps, DispatchProps, RouteComponentProps<{ id: string }> {}

export const LogUpdate = (props: ILogUpdateProps) => {
  const [isNew, setIsNew] = useState(!props.match.params || !props.match.params.id);

  const { logEntity, loading, updating } = props;

  const handleClose = () => {
    props.history.push('/log' + props.location.search);
  };

  useEffect(() => {
    if (isNew) {
      props.reset();
    } else {
      props.getEntity(props.match.params.id);
    }
  }, []);

  useEffect(() => {
    if (props.updateSuccess) {
      handleClose();
    }
  }, [props.updateSuccess]);

  const saveEntity = (event, errors, values) => {
    if (errors.length === 0) {
      const entity = {
        ...logEntity,
        ...values
      };

      if (isNew) {
        props.createEntity(entity);
      } else {
        props.updateEntity(entity);
      }
    }
  };

  return (
    <div>
      <Row className="justify-content-center">
        <Col md="8">
          <h2 id="logSystemApp.log.home.createOrEditLabel">Create or edit a Log</h2>
        </Col>
      </Row>
      <Row className="justify-content-center">
        <Col md="8">
          {loading ? (
            <p>Loading...</p>
          ) : (
            <AvForm model={isNew ? {} : logEntity} onSubmit={saveEntity}>
              {!isNew ? (
                <AvGroup>
                  <Label for="log-id">ID</Label>
                  <AvInput id="log-id" type="text" className="form-control" name="id" required readOnly />
                </AvGroup>
              ) : null}
              <AvGroup>
                <Label id="urlLabel" for="log-url">
                  Url
                </Label>
                <AvField id="log-url" type="text" name="url" />
              </AvGroup>
              <AvGroup>
                <Label id="requestItemLabel" for="log-requestItem">
                  Request Item
                </Label>
                <AvField id="log-requestItem" type="text" name="requestItem" />
              </AvGroup>
              <AvGroup>
                <Label id="responseItemLabel" for="log-responseItem">
                  Response Item
                </Label>
                <AvField id="log-responseItem" type="text" name="responseItem" />
              </AvGroup>
              <AvGroup>
                <Label id="statusLabel" for="log-status">
                  Status
                </Label>
                <AvField id="log-status" type="string" className="form-control" name="status" />
              </AvGroup>
              <AvGroup>
                <Label id="methodLabel" for="log-method">
                  Method
                </Label>
                <AvField id="log-method" type="text" name="method" />
              </AvGroup>
              <AvGroup>
                <Label id="platformLabel" for="log-platform">
                  Platform
                </Label>
                <AvField id="log-platform" type="text" name="platform" />
              </AvGroup>
              <AvGroup>
                <Label id="requestTimeLabel" for="log-requestTime">
                  Request Time
                </Label>
                <AvField id="log-requestTime" type="text" name="requestTime" />
              </AvGroup>
              <AvGroup>
                <Label id="responseTimeLabel" for="log-responseTime">
                  Response Time
                </Label>
                <AvField id="log-responseTime" type="text" name="responseTime" />
              </AvGroup>
              <AvGroup>
                <Label id="durationLabel" for="log-duration">
                  Duration
                </Label>
                <AvField id="log-duration" type="string" className="form-control" name="duration" />
              </AvGroup>
              <AvGroup check>
                <Label id="loggedLabel">
                  <AvInput id="log-logged" type="checkbox" className="form-check-input" name="logged" />
                  Logged
                </Label>
              </AvGroup>
              <AvGroup>
                <Label id="userLabel" for="log-user">
                  User
                </Label>
                <AvField id="log-user" type="text" name="user" />
              </AvGroup>
              <Button tag={Link} id="cancel-save" to="/log" replace color="info">
                <FontAwesomeIcon icon="arrow-left" />
                &nbsp;
                <span className="d-none d-md-inline">Back</span>
              </Button>
              &nbsp;
              <Button color="primary" id="save-entity" type="submit" disabled={updating}>
                <FontAwesomeIcon icon="save" />
                &nbsp; Save
              </Button>
            </AvForm>
          )}
        </Col>
      </Row>
    </div>
  );
};

const mapStateToProps = (storeState: IRootState) => ({
  logEntity: storeState.log.entity,
  loading: storeState.log.loading,
  updating: storeState.log.updating,
  updateSuccess: storeState.log.updateSuccess
});

const mapDispatchToProps = {
  getEntity,
  updateEntity,
  createEntity,
  reset
};

type StateProps = ReturnType<typeof mapStateToProps>;
type DispatchProps = typeof mapDispatchToProps;

export default connect(mapStateToProps, mapDispatchToProps)(LogUpdate);
