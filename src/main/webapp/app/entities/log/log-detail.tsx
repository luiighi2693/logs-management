import React, { useEffect } from 'react';
import { connect } from 'react-redux';
import { Link, RouteComponentProps } from 'react-router-dom';
import { Button, Row, Col } from 'reactstrap';
import { ICrudGetAction } from 'react-jhipster';
import { FontAwesomeIcon } from '@fortawesome/react-fontawesome';

import { IRootState } from 'app/shared/reducers';
import { getEntity } from './log.reducer';
import { ILog } from 'app/shared/model/log.model';
import { APP_DATE_FORMAT, APP_LOCAL_DATE_FORMAT } from 'app/config/constants';

export interface ILogDetailProps extends StateProps, DispatchProps, RouteComponentProps<{ id: string }> {}

export const LogDetail = (props: ILogDetailProps) => {
  useEffect(() => {
    props.getEntity(props.match.params.id);
  }, []);

  const { logEntity } = props;
  return (
    <Row>
      <Col md="8">
        <h2>
          Log [<b>{logEntity.id}</b>]
        </h2>
        <dl className="jh-entity-details">
          <dt>
            <span id="url">Url</span>
          </dt>
          <dd>{logEntity.url}</dd>
          <dt>
            <span id="requestItem">Request Item</span>
          </dt>
          <dd>{logEntity.requestItem}</dd>
          <dt>
            <span id="responseItem">Response Item</span>
          </dt>
          <dd>{logEntity.responseItem}</dd>
          <dt>
            <span id="status">Status</span>
          </dt>
          <dd>{logEntity.status}</dd>
          <dt>
            <span id="method">Method</span>
          </dt>
          <dd>{logEntity.method}</dd>
          <dt>
            <span id="platform">Platform</span>
          </dt>
          <dd>{logEntity.platform}</dd>
          <dt>
            <span id="requestTime">Request Time</span>
          </dt>
          <dd>{logEntity.requestTime}</dd>
          <dt>
            <span id="responseTime">Response Time</span>
          </dt>
          <dd>{logEntity.responseTime}</dd>
          <dt>
            <span id="duration">Duration</span>
          </dt>
          <dd>{logEntity.duration}</dd>
          <dt>
            <span id="logged">Logged</span>
          </dt>
          <dd>{logEntity.logged ? 'true' : 'false'}</dd>
          <dt>
            <span id="user">User</span>
          </dt>
          <dd>{logEntity.user}</dd>
        </dl>
        <Button tag={Link} to="/log" replace color="info">
          <FontAwesomeIcon icon="arrow-left" /> <span className="d-none d-md-inline">Back</span>
        </Button>
        &nbsp;
        <Button tag={Link} to={`/log/${logEntity.id}/edit`} replace color="primary">
          <FontAwesomeIcon icon="pencil-alt" /> <span className="d-none d-md-inline">Edit</span>
        </Button>
      </Col>
    </Row>
  );
};

const mapStateToProps = ({ log }: IRootState) => ({
  logEntity: log.entity
});

const mapDispatchToProps = { getEntity };

type StateProps = ReturnType<typeof mapStateToProps>;
type DispatchProps = typeof mapDispatchToProps;

export default connect(mapStateToProps, mapDispatchToProps)(LogDetail);
