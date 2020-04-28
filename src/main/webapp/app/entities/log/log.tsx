import React, { useState, useEffect } from 'react';
import { connect } from 'react-redux';
import { Link, RouteComponentProps } from 'react-router-dom';
import { Button, Col, Row, Table } from 'reactstrap';
import { ICrudGetAllAction, getSortState, IPaginationBaseState, JhiPagination, JhiItemCount } from 'react-jhipster';
import { FontAwesomeIcon } from '@fortawesome/react-fontawesome';

import { IRootState } from 'app/shared/reducers';
import { getEntities } from './log.reducer';
import { ILog } from 'app/shared/model/log.model';
import { APP_DATE_FORMAT, APP_LOCAL_DATE_FORMAT } from 'app/config/constants';
import { ITEMS_PER_PAGE } from 'app/shared/util/pagination.constants';

export interface ILogProps extends StateProps, DispatchProps, RouteComponentProps<{ url: string }> {}

export const Log = (props: ILogProps) => {
  const [paginationState, setPaginationState] = useState(getSortState(props.location, ITEMS_PER_PAGE));

  const getAllEntities = () => {
    props.getEntities(paginationState.activePage - 1, paginationState.itemsPerPage, `${paginationState.sort},${paginationState.order}`);
  };

  const sortEntities = () => {
    getAllEntities();
    props.history.push(
      `${props.location.pathname}?page=${paginationState.activePage}&sort=${paginationState.sort},${paginationState.order}`
    );
  };

  useEffect(() => {
    sortEntities();
  }, [paginationState.activePage, paginationState.order, paginationState.sort]);

  const sort = p => () => {
    setPaginationState({
      ...paginationState,
      order: paginationState.order === 'asc' ? 'desc' : 'asc',
      sort: p
    });
  };

  const handlePagination = currentPage =>
    setPaginationState({
      ...paginationState,
      activePage: currentPage
    });

  const { logList, match, loading, totalItems } = props;
  return (
    <div>
      <h2 id="log-heading">
        Logs
      </h2>
      <div className="table-responsive">
        {logList && logList.length > 0 ? (
          <Table responsive>
            <thead>
              <tr>
                <th className="hand" onClick={sort('id')}>
                  ID <FontAwesomeIcon icon="sort" />
                </th>
                <th className="hand" onClick={sort('url')}>
                  Url <FontAwesomeIcon icon="sort" />
                </th>
                <th className="hand" onClick={sort('requestItem')}>
                  Request Item <FontAwesomeIcon icon="sort" />
                </th>
                <th className="hand" onClick={sort('responseItem')}>
                  Response Item <FontAwesomeIcon icon="sort" />
                </th>
                <th className="hand" onClick={sort('status')}>
                  Status <FontAwesomeIcon icon="sort" />
                </th>
                <th className="hand" onClick={sort('method')}>
                  Method <FontAwesomeIcon icon="sort" />
                </th>
                <th className="hand" onClick={sort('platform')}>
                  Platform <FontAwesomeIcon icon="sort" />
                </th>
                <th className="hand" onClick={sort('requestTime')}>
                  Request Time <FontAwesomeIcon icon="sort" />
                </th>
                <th className="hand" onClick={sort('responseTime')}>
                  Response Time <FontAwesomeIcon icon="sort" />
                </th>
                <th className="hand" onClick={sort('duration')}>
                  Duration <FontAwesomeIcon icon="sort" />
                </th>
                <th className="hand" onClick={sort('logged')}>
                  Logged <FontAwesomeIcon icon="sort" />
                </th>
                <th className="hand" onClick={sort('user')}>
                  User <FontAwesomeIcon icon="sort" />
                </th>
                <th />
              </tr>
            </thead>
            <tbody>
              {logList.map((log, i) => (
                <tr key={`entity-${i}`}>
                  <td>
                    <Button tag={Link} to={`${match.url}/${log.id}`} color="link" size="sm">
                      {log.id}
                    </Button>
                  </td>
                  <td>{log.url}</td>
                  <td>{log.requestItem}</td>
                  <td>{log.responseItem}</td>
                  <td>{log.status}</td>
                  <td>{log.method}</td>
                  <td>{log.platform}</td>
                  <td>{log.requestTime}</td>
                  <td>{log.responseTime}</td>
                  <td>{log.duration}</td>
                  <td>{log.logged ? 'true' : 'false'}</td>
                  <td>{log.user}</td>
                  <td className="text-right">
                    <div className="btn-group flex-btn-group-container">
                      <Button tag={Link} to={`${match.url}/${log.id}`} color="info" size="sm">
                        <FontAwesomeIcon icon="eye" /> <span className="d-none d-md-inline">View</span>
                      </Button>
                    </div>
                  </td>
                </tr>
              ))}
            </tbody>
          </Table>
        ) : (
          !loading && <div className="alert alert-warning">No Logs found</div>
        )}
      </div>
      <div className={logList && logList.length > 0 ? '' : 'd-none'}>
        <Row className="justify-content-center">
          <JhiItemCount page={paginationState.activePage} total={totalItems} itemsPerPage={paginationState.itemsPerPage} />
        </Row>
        <Row className="justify-content-center">
          <JhiPagination
            activePage={paginationState.activePage}
            onSelect={handlePagination}
            maxButtons={5}
            itemsPerPage={paginationState.itemsPerPage}
            totalItems={props.totalItems}
          />
        </Row>
      </div>
    </div>
  );
};

const mapStateToProps = ({ log }: IRootState) => ({
  logList: log.entities,
  loading: log.loading,
  totalItems: log.totalItems
});

const mapDispatchToProps = {
  getEntities
};

type StateProps = ReturnType<typeof mapStateToProps>;
type DispatchProps = typeof mapDispatchToProps;

export default connect(mapStateToProps, mapDispatchToProps)(Log);
