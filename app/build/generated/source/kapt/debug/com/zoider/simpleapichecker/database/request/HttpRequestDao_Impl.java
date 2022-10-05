package com.zoider.simpleapichecker.database.request;

import android.database.Cursor;
import androidx.lifecycle.LiveData;
import androidx.room.CoroutinesRoom;
import androidx.room.EntityDeletionOrUpdateAdapter;
import androidx.room.EntityInsertionAdapter;
import androidx.room.RoomDatabase;
import androidx.room.RoomSQLiteQuery;
import androidx.room.util.CursorUtil;
import androidx.room.util.DBUtil;
import androidx.sqlite.db.SupportSQLiteStatement;
import com.zoider.simpleapichecker.commons.HttpMethod;
import com.zoider.simpleapichecker.database.HttpMethodConverter;
import java.lang.Class;
import java.lang.Exception;
import java.lang.Long;
import java.lang.Object;
import java.lang.Override;
import java.lang.String;
import java.lang.SuppressWarnings;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.concurrent.Callable;
import javax.annotation.processing.Generated;
import kotlin.Unit;
import kotlin.coroutines.Continuation;

@Generated("androidx.room.RoomProcessor")
@SuppressWarnings({"unchecked", "deprecation"})
public final class HttpRequestDao_Impl implements HttpRequestDao {
  private final RoomDatabase __db;

  private final EntityInsertionAdapter<HttpRequest> __insertionAdapterOfHttpRequest;

  private HttpMethodConverter __httpMethodConverter;

  private final EntityDeletionOrUpdateAdapter<HttpRequest> __deletionAdapterOfHttpRequest;

  public HttpRequestDao_Impl(RoomDatabase __db) {
    this.__db = __db;
    this.__insertionAdapterOfHttpRequest = new EntityInsertionAdapter<HttpRequest>(__db) {
      @Override
      public String createQuery() {
        return "INSERT OR ABORT INTO `HttpRequest` (`id`,`method`,`url`) VALUES (nullif(?, 0),?,?)";
      }

      @Override
      public void bind(SupportSQLiteStatement stmt, HttpRequest value) {
        stmt.bindLong(1, value.getId());
        final int _tmp = __httpMethodConverter().fromHttpMethod(value.getMethod());
        stmt.bindLong(2, _tmp);
        if (value.getUrl() == null) {
          stmt.bindNull(3);
        } else {
          stmt.bindString(3, value.getUrl());
        }
      }
    };
    this.__deletionAdapterOfHttpRequest = new EntityDeletionOrUpdateAdapter<HttpRequest>(__db) {
      @Override
      public String createQuery() {
        return "DELETE FROM `HttpRequest` WHERE `id` = ?";
      }

      @Override
      public void bind(SupportSQLiteStatement stmt, HttpRequest value) {
        stmt.bindLong(1, value.getId());
      }
    };
  }

  @Override
  public Object insert(final HttpRequest request, final Continuation<? super Long> continuation) {
    return CoroutinesRoom.execute(__db, true, new Callable<Long>() {
      @Override
      public Long call() throws Exception {
        __db.beginTransaction();
        try {
          long _result = __insertionAdapterOfHttpRequest.insertAndReturnId(request);
          __db.setTransactionSuccessful();
          return _result;
        } finally {
          __db.endTransaction();
        }
      }
    }, continuation);
  }

  @Override
  public Object delete(final HttpRequest request, final Continuation<? super Unit> continuation) {
    return CoroutinesRoom.execute(__db, true, new Callable<Unit>() {
      @Override
      public Unit call() throws Exception {
        __db.beginTransaction();
        try {
          __deletionAdapterOfHttpRequest.handle(request);
          __db.setTransactionSuccessful();
          return Unit.INSTANCE;
        } finally {
          __db.endTransaction();
        }
      }
    }, continuation);
  }

  @Override
  public LiveData<List<HttpRequest>> getAll() {
    final String _sql = "SELECT * FROM HttpRequest";
    final RoomSQLiteQuery _statement = RoomSQLiteQuery.acquire(_sql, 0);
    return __db.getInvalidationTracker().createLiveData(new String[]{"HttpRequest"}, false, new Callable<List<HttpRequest>>() {
      @Override
      public List<HttpRequest> call() throws Exception {
        final Cursor _cursor = DBUtil.query(__db, _statement, false, null);
        try {
          final int _cursorIndexOfId = CursorUtil.getColumnIndexOrThrow(_cursor, "id");
          final int _cursorIndexOfMethod = CursorUtil.getColumnIndexOrThrow(_cursor, "method");
          final int _cursorIndexOfUrl = CursorUtil.getColumnIndexOrThrow(_cursor, "url");
          final List<HttpRequest> _result = new ArrayList<HttpRequest>(_cursor.getCount());
          while(_cursor.moveToNext()) {
            final HttpRequest _item;
            final int _tmpId;
            _tmpId = _cursor.getInt(_cursorIndexOfId);
            final HttpMethod _tmpMethod;
            final int _tmp;
            _tmp = _cursor.getInt(_cursorIndexOfMethod);
            _tmpMethod = __httpMethodConverter().toHttpMethod(_tmp);
            final String _tmpUrl;
            if (_cursor.isNull(_cursorIndexOfUrl)) {
              _tmpUrl = null;
            } else {
              _tmpUrl = _cursor.getString(_cursorIndexOfUrl);
            }
            _item = new HttpRequest(_tmpId,_tmpMethod,_tmpUrl);
            _result.add(_item);
          }
          return _result;
        } finally {
          _cursor.close();
        }
      }

      @Override
      protected void finalize() {
        _statement.release();
      }
    });
  }

  @Override
  public LiveData<HttpRequest> getById(final int id) {
    final String _sql = "SELECT * FROM HttpRequest WHERE id=?";
    final RoomSQLiteQuery _statement = RoomSQLiteQuery.acquire(_sql, 1);
    int _argIndex = 1;
    _statement.bindLong(_argIndex, id);
    return __db.getInvalidationTracker().createLiveData(new String[]{"HttpRequest"}, false, new Callable<HttpRequest>() {
      @Override
      public HttpRequest call() throws Exception {
        final Cursor _cursor = DBUtil.query(__db, _statement, false, null);
        try {
          final int _cursorIndexOfId = CursorUtil.getColumnIndexOrThrow(_cursor, "id");
          final int _cursorIndexOfMethod = CursorUtil.getColumnIndexOrThrow(_cursor, "method");
          final int _cursorIndexOfUrl = CursorUtil.getColumnIndexOrThrow(_cursor, "url");
          final HttpRequest _result;
          if(_cursor.moveToFirst()) {
            final int _tmpId;
            _tmpId = _cursor.getInt(_cursorIndexOfId);
            final HttpMethod _tmpMethod;
            final int _tmp;
            _tmp = _cursor.getInt(_cursorIndexOfMethod);
            _tmpMethod = __httpMethodConverter().toHttpMethod(_tmp);
            final String _tmpUrl;
            if (_cursor.isNull(_cursorIndexOfUrl)) {
              _tmpUrl = null;
            } else {
              _tmpUrl = _cursor.getString(_cursorIndexOfUrl);
            }
            _result = new HttpRequest(_tmpId,_tmpMethod,_tmpUrl);
          } else {
            _result = null;
          }
          return _result;
        } finally {
          _cursor.close();
        }
      }

      @Override
      protected void finalize() {
        _statement.release();
      }
    });
  }

  public static List<Class<?>> getRequiredConverters() {
    return Arrays.asList(HttpMethodConverter.class);
  }

  private synchronized HttpMethodConverter __httpMethodConverter() {
    if (__httpMethodConverter == null) {
      __httpMethodConverter = __db.getTypeConverter(HttpMethodConverter.class);
    }
    return __httpMethodConverter;
  }
}
