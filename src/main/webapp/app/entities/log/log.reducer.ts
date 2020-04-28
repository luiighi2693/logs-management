import axios from 'axios';
import { ICrudGetAction, ICrudGetAllAction, ICrudPutAction, ICrudDeleteAction } from 'react-jhipster';

import { cleanEntity } from 'app/shared/util/entity-utils';
import { REQUEST, SUCCESS, FAILURE } from 'app/shared/reducers/action-type.util';

import { ILog, defaultValue } from 'app/shared/model/log.model';

export const ACTION_TYPES = {
  FETCH_LOG_LIST: 'log/FETCH_LOG_LIST',
  FETCH_LOG: 'log/FETCH_LOG',
  CREATE_LOG: 'log/CREATE_LOG',
  UPDATE_LOG: 'log/UPDATE_LOG',
  DELETE_LOG: 'log/DELETE_LOG',
  RESET: 'log/RESET'
};

const initialState = {
  loading: false,
  errorMessage: null,
  entities: [] as ReadonlyArray<ILog>,
  entity: defaultValue,
  updating: false,
  totalItems: 0,
  updateSuccess: false
};

export type LogState = Readonly<typeof initialState>;

// Reducer

function formatList(data: any[]) {
  data.forEach(item => {
    item.id = item.id.toString().substr(1, item.id.toString().length - 2);
    item.url = item.url.toString().substr(1, item.url.toString().length - 2);
    item.method = item.method.toString().substr(1, item.method.toString().length - 2);
    item.platform = item.platform.toString().substr(1, item.platform.toString().length - 2);
    item.platform = item.platform.replace(/\\/g, '');
    item.requestItem = item.requestItem.toString().substr(1, item.requestItem.toString().length - 2);
    item.requestItem = item.requestItem.replace(/\\/g, '');
    item.requestTime = item.requestTime.toString().substr(1, item.requestTime.toString().length - 2);
    item.responseItem = item.responseItem.toString().substr(1, item.responseItem.toString().length - 2);
    item.responseItem = item.responseItem.replace(/\\/g, '');
    item.responseTime = item.responseTime.toString().substr(1, item.responseTime.toString().length - 2);
    item.user = item.user.toString().substr(1, item.user.toString().length - 2);
    item.user = item.user.replace(/\\/g, '');
  });
  return data;
}

function formatItem(item: any) {
  item.id = item.id.toString().substr(1, item.id.toString().length - 2);
  item.url = item.url.toString().substr(1, item.url.toString().length - 2);
  item.method = item.method.toString().substr(1, item.method.toString().length - 2);
  item.platform = item.platform.toString().substr(1, item.platform.toString().length - 2);
  item.platform = item.platform.replace(/\\/g, '');
  item.requestItem = item.requestItem.toString().substr(1, item.requestItem.toString().length - 2);
  item.requestItem = item.requestItem.replace(/\\/g, '');
  item.requestTime = item.requestTime.toString().substr(1, item.requestTime.toString().length - 2);
  item.responseItem = item.responseItem.toString().substr(1, item.responseItem.toString().length - 2);
  item.responseItem = item.responseItem.replace(/\\/g, '');
  item.responseTime = item.responseTime.toString().substr(1, item.responseTime.toString().length - 2);
  item.user = item.user.toString().substr(1, item.user.toString().length - 2);
  item.user = item.user.replace(/\\/g, '');
  return item;
}

export default (state: LogState = initialState, action): LogState => {
  switch (action.type) {
    case REQUEST(ACTION_TYPES.FETCH_LOG_LIST):
    case REQUEST(ACTION_TYPES.FETCH_LOG):
      return {
        ...state,
        errorMessage: null,
        updateSuccess: false,
        loading: true
      };
    case REQUEST(ACTION_TYPES.CREATE_LOG):
    case REQUEST(ACTION_TYPES.UPDATE_LOG):
    case REQUEST(ACTION_TYPES.DELETE_LOG):
      return {
        ...state,
        errorMessage: null,
        updateSuccess: false,
        updating: true
      };
    case FAILURE(ACTION_TYPES.FETCH_LOG_LIST):
    case FAILURE(ACTION_TYPES.FETCH_LOG):
    case FAILURE(ACTION_TYPES.CREATE_LOG):
    case FAILURE(ACTION_TYPES.UPDATE_LOG):
    case FAILURE(ACTION_TYPES.DELETE_LOG):
      return {
        ...state,
        loading: false,
        updating: false,
        updateSuccess: false,
        errorMessage: action.payload
      };
    case SUCCESS(ACTION_TYPES.FETCH_LOG_LIST):
      return {
        ...state,
        loading: false,
        entities: formatList(action.payload.data),
        totalItems: parseInt(action.payload.headers['x-total-count'], 10)
      };
    case SUCCESS(ACTION_TYPES.FETCH_LOG):
      return {
        ...state,
        loading: false,
        entity: formatItem(action.payload.data)
      };
    case SUCCESS(ACTION_TYPES.CREATE_LOG):
    case SUCCESS(ACTION_TYPES.UPDATE_LOG):
      return {
        ...state,
        updating: false,
        updateSuccess: true,
        entity: action.payload.data
      };
    case SUCCESS(ACTION_TYPES.DELETE_LOG):
      return {
        ...state,
        updating: false,
        updateSuccess: true,
        entity: {}
      };
    case ACTION_TYPES.RESET:
      return {
        ...initialState
      };
    default:
      return state;
  }
};

const apiUrl = 'api/logs';

// Actions

export const getEntities: ICrudGetAllAction<ILog> = (page, size, sort) => {
  const requestUrl = `${apiUrl}${sort ? `?page=${page}&size=${size}&sort=${sort}` : ''}`;
  return {
    type: ACTION_TYPES.FETCH_LOG_LIST,
    payload: axios.get<ILog>(`${requestUrl}${sort ? '&' : '?'}cacheBuster=${new Date().getTime()}`)
  };
};

export const getEntity: ICrudGetAction<ILog> = id => {
  const requestUrl = `${apiUrl}/${id}`;
  return {
    type: ACTION_TYPES.FETCH_LOG,
    payload: axios.get<ILog>(requestUrl)
  };
};

export const createEntity: ICrudPutAction<ILog> = entity => async dispatch => {
  const result = await dispatch({
    type: ACTION_TYPES.CREATE_LOG,
    payload: axios.post(apiUrl, cleanEntity(entity))
  });
  dispatch(getEntities());
  return result;
};

export const updateEntity: ICrudPutAction<ILog> = entity => async dispatch => {
  const result = await dispatch({
    type: ACTION_TYPES.UPDATE_LOG,
    payload: axios.put(apiUrl, cleanEntity(entity))
  });
  return result;
};

export const deleteEntity: ICrudDeleteAction<ILog> = id => async dispatch => {
  const requestUrl = `${apiUrl}/${id}`;
  const result = await dispatch({
    type: ACTION_TYPES.DELETE_LOG,
    payload: axios.delete(requestUrl)
  });
  return result;
};

export const reset = () => ({
  type: ACTION_TYPES.RESET
});
