package com.kaiganews.app.data.local.dao;

import android.database.Cursor;
import android.os.CancellationSignal;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.room.CoroutinesRoom;
import androidx.room.EntityDeletionOrUpdateAdapter;
import androidx.room.EntityInsertionAdapter;
import androidx.room.RoomDatabase;
import androidx.room.RoomSQLiteQuery;
import androidx.room.SharedSQLiteStatement;
import androidx.room.util.CursorUtil;
import androidx.room.util.DBUtil;
import androidx.sqlite.db.SupportSQLiteStatement;
import com.kaiganews.app.data.local.entity.ArticleEntity;
import java.lang.Class;
import java.lang.Exception;
import java.lang.Integer;
import java.lang.Object;
import java.lang.Override;
import java.lang.String;
import java.lang.SuppressWarnings;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.concurrent.Callable;
import javax.annotation.processing.Generated;
import kotlin.Unit;
import kotlin.coroutines.Continuation;
import kotlinx.coroutines.flow.Flow;

@Generated("androidx.room.RoomProcessor")
@SuppressWarnings({"unchecked", "deprecation"})
public final class ArticleDao_Impl implements ArticleDao {
  private final RoomDatabase __db;

  private final EntityInsertionAdapter<ArticleEntity> __insertionAdapterOfArticleEntity;

  private final EntityDeletionOrUpdateAdapter<ArticleEntity> __updateAdapterOfArticleEntity;

  private final SharedSQLiteStatement __preparedStmtOfUpdateBookmark;

  private final SharedSQLiteStatement __preparedStmtOfDeleteOldArticles;

  public ArticleDao_Impl(@NonNull final RoomDatabase __db) {
    this.__db = __db;
    this.__insertionAdapterOfArticleEntity = new EntityInsertionAdapter<ArticleEntity>(__db) {
      @Override
      @NonNull
      protected String createQuery() {
        return "INSERT OR REPLACE INTO `articles` (`id`,`title`,`description`,`content`,`imageUrl`,`link`,`author`,`publishedAt`,`categories`,`isBookmarked`,`cachedAt`) VALUES (?,?,?,?,?,?,?,?,?,?,?)";
      }

      @Override
      protected void bind(@NonNull final SupportSQLiteStatement statement,
          @NonNull final ArticleEntity entity) {
        statement.bindString(1, entity.getId());
        statement.bindString(2, entity.getTitle());
        statement.bindString(3, entity.getDescription());
        statement.bindString(4, entity.getContent());
        if (entity.getImageUrl() == null) {
          statement.bindNull(5);
        } else {
          statement.bindString(5, entity.getImageUrl());
        }
        statement.bindString(6, entity.getLink());
        statement.bindString(7, entity.getAuthor());
        statement.bindLong(8, entity.getPublishedAt());
        statement.bindString(9, entity.getCategories());
        final int _tmp = entity.isBookmarked() ? 1 : 0;
        statement.bindLong(10, _tmp);
        statement.bindLong(11, entity.getCachedAt());
      }
    };
    this.__updateAdapterOfArticleEntity = new EntityDeletionOrUpdateAdapter<ArticleEntity>(__db) {
      @Override
      @NonNull
      protected String createQuery() {
        return "UPDATE OR ABORT `articles` SET `id` = ?,`title` = ?,`description` = ?,`content` = ?,`imageUrl` = ?,`link` = ?,`author` = ?,`publishedAt` = ?,`categories` = ?,`isBookmarked` = ?,`cachedAt` = ? WHERE `id` = ?";
      }

      @Override
      protected void bind(@NonNull final SupportSQLiteStatement statement,
          @NonNull final ArticleEntity entity) {
        statement.bindString(1, entity.getId());
        statement.bindString(2, entity.getTitle());
        statement.bindString(3, entity.getDescription());
        statement.bindString(4, entity.getContent());
        if (entity.getImageUrl() == null) {
          statement.bindNull(5);
        } else {
          statement.bindString(5, entity.getImageUrl());
        }
        statement.bindString(6, entity.getLink());
        statement.bindString(7, entity.getAuthor());
        statement.bindLong(8, entity.getPublishedAt());
        statement.bindString(9, entity.getCategories());
        final int _tmp = entity.isBookmarked() ? 1 : 0;
        statement.bindLong(10, _tmp);
        statement.bindLong(11, entity.getCachedAt());
        statement.bindString(12, entity.getId());
      }
    };
    this.__preparedStmtOfUpdateBookmark = new SharedSQLiteStatement(__db) {
      @Override
      @NonNull
      public String createQuery() {
        final String _query = "UPDATE articles SET isBookmarked = ? WHERE id = ?";
        return _query;
      }
    };
    this.__preparedStmtOfDeleteOldArticles = new SharedSQLiteStatement(__db) {
      @Override
      @NonNull
      public String createQuery() {
        final String _query = "DELETE FROM articles WHERE cachedAt < ?";
        return _query;
      }
    };
  }

  @Override
  public Object insertArticles(final List<ArticleEntity> articles,
      final Continuation<? super Unit> $completion) {
    return CoroutinesRoom.execute(__db, true, new Callable<Unit>() {
      @Override
      @NonNull
      public Unit call() throws Exception {
        __db.beginTransaction();
        try {
          __insertionAdapterOfArticleEntity.insert(articles);
          __db.setTransactionSuccessful();
          return Unit.INSTANCE;
        } finally {
          __db.endTransaction();
        }
      }
    }, $completion);
  }

  @Override
  public Object insertArticle(final ArticleEntity article,
      final Continuation<? super Unit> $completion) {
    return CoroutinesRoom.execute(__db, true, new Callable<Unit>() {
      @Override
      @NonNull
      public Unit call() throws Exception {
        __db.beginTransaction();
        try {
          __insertionAdapterOfArticleEntity.insert(article);
          __db.setTransactionSuccessful();
          return Unit.INSTANCE;
        } finally {
          __db.endTransaction();
        }
      }
    }, $completion);
  }

  @Override
  public Object updateArticle(final ArticleEntity article,
      final Continuation<? super Unit> $completion) {
    return CoroutinesRoom.execute(__db, true, new Callable<Unit>() {
      @Override
      @NonNull
      public Unit call() throws Exception {
        __db.beginTransaction();
        try {
          __updateAdapterOfArticleEntity.handle(article);
          __db.setTransactionSuccessful();
          return Unit.INSTANCE;
        } finally {
          __db.endTransaction();
        }
      }
    }, $completion);
  }

  @Override
  public Object updateBookmark(final String articleId, final boolean isBookmarked,
      final Continuation<? super Unit> $completion) {
    return CoroutinesRoom.execute(__db, true, new Callable<Unit>() {
      @Override
      @NonNull
      public Unit call() throws Exception {
        final SupportSQLiteStatement _stmt = __preparedStmtOfUpdateBookmark.acquire();
        int _argIndex = 1;
        final int _tmp = isBookmarked ? 1 : 0;
        _stmt.bindLong(_argIndex, _tmp);
        _argIndex = 2;
        _stmt.bindString(_argIndex, articleId);
        try {
          __db.beginTransaction();
          try {
            _stmt.executeUpdateDelete();
            __db.setTransactionSuccessful();
            return Unit.INSTANCE;
          } finally {
            __db.endTransaction();
          }
        } finally {
          __preparedStmtOfUpdateBookmark.release(_stmt);
        }
      }
    }, $completion);
  }

  @Override
  public Object deleteOldArticles(final long timestamp,
      final Continuation<? super Unit> $completion) {
    return CoroutinesRoom.execute(__db, true, new Callable<Unit>() {
      @Override
      @NonNull
      public Unit call() throws Exception {
        final SupportSQLiteStatement _stmt = __preparedStmtOfDeleteOldArticles.acquire();
        int _argIndex = 1;
        _stmt.bindLong(_argIndex, timestamp);
        try {
          __db.beginTransaction();
          try {
            _stmt.executeUpdateDelete();
            __db.setTransactionSuccessful();
            return Unit.INSTANCE;
          } finally {
            __db.endTransaction();
          }
        } finally {
          __preparedStmtOfDeleteOldArticles.release(_stmt);
        }
      }
    }, $completion);
  }

  @Override
  public Flow<List<ArticleEntity>> getAllArticles() {
    final String _sql = "SELECT * FROM articles ORDER BY publishedAt DESC";
    final RoomSQLiteQuery _statement = RoomSQLiteQuery.acquire(_sql, 0);
    return CoroutinesRoom.createFlow(__db, false, new String[] {"articles"}, new Callable<List<ArticleEntity>>() {
      @Override
      @NonNull
      public List<ArticleEntity> call() throws Exception {
        final Cursor _cursor = DBUtil.query(__db, _statement, false, null);
        try {
          final int _cursorIndexOfId = CursorUtil.getColumnIndexOrThrow(_cursor, "id");
          final int _cursorIndexOfTitle = CursorUtil.getColumnIndexOrThrow(_cursor, "title");
          final int _cursorIndexOfDescription = CursorUtil.getColumnIndexOrThrow(_cursor, "description");
          final int _cursorIndexOfContent = CursorUtil.getColumnIndexOrThrow(_cursor, "content");
          final int _cursorIndexOfImageUrl = CursorUtil.getColumnIndexOrThrow(_cursor, "imageUrl");
          final int _cursorIndexOfLink = CursorUtil.getColumnIndexOrThrow(_cursor, "link");
          final int _cursorIndexOfAuthor = CursorUtil.getColumnIndexOrThrow(_cursor, "author");
          final int _cursorIndexOfPublishedAt = CursorUtil.getColumnIndexOrThrow(_cursor, "publishedAt");
          final int _cursorIndexOfCategories = CursorUtil.getColumnIndexOrThrow(_cursor, "categories");
          final int _cursorIndexOfIsBookmarked = CursorUtil.getColumnIndexOrThrow(_cursor, "isBookmarked");
          final int _cursorIndexOfCachedAt = CursorUtil.getColumnIndexOrThrow(_cursor, "cachedAt");
          final List<ArticleEntity> _result = new ArrayList<ArticleEntity>(_cursor.getCount());
          while (_cursor.moveToNext()) {
            final ArticleEntity _item;
            final String _tmpId;
            _tmpId = _cursor.getString(_cursorIndexOfId);
            final String _tmpTitle;
            _tmpTitle = _cursor.getString(_cursorIndexOfTitle);
            final String _tmpDescription;
            _tmpDescription = _cursor.getString(_cursorIndexOfDescription);
            final String _tmpContent;
            _tmpContent = _cursor.getString(_cursorIndexOfContent);
            final String _tmpImageUrl;
            if (_cursor.isNull(_cursorIndexOfImageUrl)) {
              _tmpImageUrl = null;
            } else {
              _tmpImageUrl = _cursor.getString(_cursorIndexOfImageUrl);
            }
            final String _tmpLink;
            _tmpLink = _cursor.getString(_cursorIndexOfLink);
            final String _tmpAuthor;
            _tmpAuthor = _cursor.getString(_cursorIndexOfAuthor);
            final long _tmpPublishedAt;
            _tmpPublishedAt = _cursor.getLong(_cursorIndexOfPublishedAt);
            final String _tmpCategories;
            _tmpCategories = _cursor.getString(_cursorIndexOfCategories);
            final boolean _tmpIsBookmarked;
            final int _tmp;
            _tmp = _cursor.getInt(_cursorIndexOfIsBookmarked);
            _tmpIsBookmarked = _tmp != 0;
            final long _tmpCachedAt;
            _tmpCachedAt = _cursor.getLong(_cursorIndexOfCachedAt);
            _item = new ArticleEntity(_tmpId,_tmpTitle,_tmpDescription,_tmpContent,_tmpImageUrl,_tmpLink,_tmpAuthor,_tmpPublishedAt,_tmpCategories,_tmpIsBookmarked,_tmpCachedAt);
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
  public Flow<List<ArticleEntity>> getArticlesByCategory(final String category) {
    final String _sql = "SELECT * FROM articles WHERE categories LIKE '%' || ? || '%' ORDER BY publishedAt DESC";
    final RoomSQLiteQuery _statement = RoomSQLiteQuery.acquire(_sql, 1);
    int _argIndex = 1;
    _statement.bindString(_argIndex, category);
    return CoroutinesRoom.createFlow(__db, false, new String[] {"articles"}, new Callable<List<ArticleEntity>>() {
      @Override
      @NonNull
      public List<ArticleEntity> call() throws Exception {
        final Cursor _cursor = DBUtil.query(__db, _statement, false, null);
        try {
          final int _cursorIndexOfId = CursorUtil.getColumnIndexOrThrow(_cursor, "id");
          final int _cursorIndexOfTitle = CursorUtil.getColumnIndexOrThrow(_cursor, "title");
          final int _cursorIndexOfDescription = CursorUtil.getColumnIndexOrThrow(_cursor, "description");
          final int _cursorIndexOfContent = CursorUtil.getColumnIndexOrThrow(_cursor, "content");
          final int _cursorIndexOfImageUrl = CursorUtil.getColumnIndexOrThrow(_cursor, "imageUrl");
          final int _cursorIndexOfLink = CursorUtil.getColumnIndexOrThrow(_cursor, "link");
          final int _cursorIndexOfAuthor = CursorUtil.getColumnIndexOrThrow(_cursor, "author");
          final int _cursorIndexOfPublishedAt = CursorUtil.getColumnIndexOrThrow(_cursor, "publishedAt");
          final int _cursorIndexOfCategories = CursorUtil.getColumnIndexOrThrow(_cursor, "categories");
          final int _cursorIndexOfIsBookmarked = CursorUtil.getColumnIndexOrThrow(_cursor, "isBookmarked");
          final int _cursorIndexOfCachedAt = CursorUtil.getColumnIndexOrThrow(_cursor, "cachedAt");
          final List<ArticleEntity> _result = new ArrayList<ArticleEntity>(_cursor.getCount());
          while (_cursor.moveToNext()) {
            final ArticleEntity _item;
            final String _tmpId;
            _tmpId = _cursor.getString(_cursorIndexOfId);
            final String _tmpTitle;
            _tmpTitle = _cursor.getString(_cursorIndexOfTitle);
            final String _tmpDescription;
            _tmpDescription = _cursor.getString(_cursorIndexOfDescription);
            final String _tmpContent;
            _tmpContent = _cursor.getString(_cursorIndexOfContent);
            final String _tmpImageUrl;
            if (_cursor.isNull(_cursorIndexOfImageUrl)) {
              _tmpImageUrl = null;
            } else {
              _tmpImageUrl = _cursor.getString(_cursorIndexOfImageUrl);
            }
            final String _tmpLink;
            _tmpLink = _cursor.getString(_cursorIndexOfLink);
            final String _tmpAuthor;
            _tmpAuthor = _cursor.getString(_cursorIndexOfAuthor);
            final long _tmpPublishedAt;
            _tmpPublishedAt = _cursor.getLong(_cursorIndexOfPublishedAt);
            final String _tmpCategories;
            _tmpCategories = _cursor.getString(_cursorIndexOfCategories);
            final boolean _tmpIsBookmarked;
            final int _tmp;
            _tmp = _cursor.getInt(_cursorIndexOfIsBookmarked);
            _tmpIsBookmarked = _tmp != 0;
            final long _tmpCachedAt;
            _tmpCachedAt = _cursor.getLong(_cursorIndexOfCachedAt);
            _item = new ArticleEntity(_tmpId,_tmpTitle,_tmpDescription,_tmpContent,_tmpImageUrl,_tmpLink,_tmpAuthor,_tmpPublishedAt,_tmpCategories,_tmpIsBookmarked,_tmpCachedAt);
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
  public Flow<ArticleEntity> getArticleById(final String id) {
    final String _sql = "SELECT * FROM articles WHERE id = ?";
    final RoomSQLiteQuery _statement = RoomSQLiteQuery.acquire(_sql, 1);
    int _argIndex = 1;
    _statement.bindString(_argIndex, id);
    return CoroutinesRoom.createFlow(__db, false, new String[] {"articles"}, new Callable<ArticleEntity>() {
      @Override
      @Nullable
      public ArticleEntity call() throws Exception {
        final Cursor _cursor = DBUtil.query(__db, _statement, false, null);
        try {
          final int _cursorIndexOfId = CursorUtil.getColumnIndexOrThrow(_cursor, "id");
          final int _cursorIndexOfTitle = CursorUtil.getColumnIndexOrThrow(_cursor, "title");
          final int _cursorIndexOfDescription = CursorUtil.getColumnIndexOrThrow(_cursor, "description");
          final int _cursorIndexOfContent = CursorUtil.getColumnIndexOrThrow(_cursor, "content");
          final int _cursorIndexOfImageUrl = CursorUtil.getColumnIndexOrThrow(_cursor, "imageUrl");
          final int _cursorIndexOfLink = CursorUtil.getColumnIndexOrThrow(_cursor, "link");
          final int _cursorIndexOfAuthor = CursorUtil.getColumnIndexOrThrow(_cursor, "author");
          final int _cursorIndexOfPublishedAt = CursorUtil.getColumnIndexOrThrow(_cursor, "publishedAt");
          final int _cursorIndexOfCategories = CursorUtil.getColumnIndexOrThrow(_cursor, "categories");
          final int _cursorIndexOfIsBookmarked = CursorUtil.getColumnIndexOrThrow(_cursor, "isBookmarked");
          final int _cursorIndexOfCachedAt = CursorUtil.getColumnIndexOrThrow(_cursor, "cachedAt");
          final ArticleEntity _result;
          if (_cursor.moveToFirst()) {
            final String _tmpId;
            _tmpId = _cursor.getString(_cursorIndexOfId);
            final String _tmpTitle;
            _tmpTitle = _cursor.getString(_cursorIndexOfTitle);
            final String _tmpDescription;
            _tmpDescription = _cursor.getString(_cursorIndexOfDescription);
            final String _tmpContent;
            _tmpContent = _cursor.getString(_cursorIndexOfContent);
            final String _tmpImageUrl;
            if (_cursor.isNull(_cursorIndexOfImageUrl)) {
              _tmpImageUrl = null;
            } else {
              _tmpImageUrl = _cursor.getString(_cursorIndexOfImageUrl);
            }
            final String _tmpLink;
            _tmpLink = _cursor.getString(_cursorIndexOfLink);
            final String _tmpAuthor;
            _tmpAuthor = _cursor.getString(_cursorIndexOfAuthor);
            final long _tmpPublishedAt;
            _tmpPublishedAt = _cursor.getLong(_cursorIndexOfPublishedAt);
            final String _tmpCategories;
            _tmpCategories = _cursor.getString(_cursorIndexOfCategories);
            final boolean _tmpIsBookmarked;
            final int _tmp;
            _tmp = _cursor.getInt(_cursorIndexOfIsBookmarked);
            _tmpIsBookmarked = _tmp != 0;
            final long _tmpCachedAt;
            _tmpCachedAt = _cursor.getLong(_cursorIndexOfCachedAt);
            _result = new ArticleEntity(_tmpId,_tmpTitle,_tmpDescription,_tmpContent,_tmpImageUrl,_tmpLink,_tmpAuthor,_tmpPublishedAt,_tmpCategories,_tmpIsBookmarked,_tmpCachedAt);
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

  @Override
  public Flow<List<ArticleEntity>> searchArticles(final String query) {
    final String _sql = "SELECT * FROM articles WHERE title LIKE '%' || ? || '%' OR description LIKE '%' || ? || '%' OR content LIKE '%' || ? || '%' ORDER BY publishedAt DESC";
    final RoomSQLiteQuery _statement = RoomSQLiteQuery.acquire(_sql, 3);
    int _argIndex = 1;
    _statement.bindString(_argIndex, query);
    _argIndex = 2;
    _statement.bindString(_argIndex, query);
    _argIndex = 3;
    _statement.bindString(_argIndex, query);
    return CoroutinesRoom.createFlow(__db, false, new String[] {"articles"}, new Callable<List<ArticleEntity>>() {
      @Override
      @NonNull
      public List<ArticleEntity> call() throws Exception {
        final Cursor _cursor = DBUtil.query(__db, _statement, false, null);
        try {
          final int _cursorIndexOfId = CursorUtil.getColumnIndexOrThrow(_cursor, "id");
          final int _cursorIndexOfTitle = CursorUtil.getColumnIndexOrThrow(_cursor, "title");
          final int _cursorIndexOfDescription = CursorUtil.getColumnIndexOrThrow(_cursor, "description");
          final int _cursorIndexOfContent = CursorUtil.getColumnIndexOrThrow(_cursor, "content");
          final int _cursorIndexOfImageUrl = CursorUtil.getColumnIndexOrThrow(_cursor, "imageUrl");
          final int _cursorIndexOfLink = CursorUtil.getColumnIndexOrThrow(_cursor, "link");
          final int _cursorIndexOfAuthor = CursorUtil.getColumnIndexOrThrow(_cursor, "author");
          final int _cursorIndexOfPublishedAt = CursorUtil.getColumnIndexOrThrow(_cursor, "publishedAt");
          final int _cursorIndexOfCategories = CursorUtil.getColumnIndexOrThrow(_cursor, "categories");
          final int _cursorIndexOfIsBookmarked = CursorUtil.getColumnIndexOrThrow(_cursor, "isBookmarked");
          final int _cursorIndexOfCachedAt = CursorUtil.getColumnIndexOrThrow(_cursor, "cachedAt");
          final List<ArticleEntity> _result = new ArrayList<ArticleEntity>(_cursor.getCount());
          while (_cursor.moveToNext()) {
            final ArticleEntity _item;
            final String _tmpId;
            _tmpId = _cursor.getString(_cursorIndexOfId);
            final String _tmpTitle;
            _tmpTitle = _cursor.getString(_cursorIndexOfTitle);
            final String _tmpDescription;
            _tmpDescription = _cursor.getString(_cursorIndexOfDescription);
            final String _tmpContent;
            _tmpContent = _cursor.getString(_cursorIndexOfContent);
            final String _tmpImageUrl;
            if (_cursor.isNull(_cursorIndexOfImageUrl)) {
              _tmpImageUrl = null;
            } else {
              _tmpImageUrl = _cursor.getString(_cursorIndexOfImageUrl);
            }
            final String _tmpLink;
            _tmpLink = _cursor.getString(_cursorIndexOfLink);
            final String _tmpAuthor;
            _tmpAuthor = _cursor.getString(_cursorIndexOfAuthor);
            final long _tmpPublishedAt;
            _tmpPublishedAt = _cursor.getLong(_cursorIndexOfPublishedAt);
            final String _tmpCategories;
            _tmpCategories = _cursor.getString(_cursorIndexOfCategories);
            final boolean _tmpIsBookmarked;
            final int _tmp;
            _tmp = _cursor.getInt(_cursorIndexOfIsBookmarked);
            _tmpIsBookmarked = _tmp != 0;
            final long _tmpCachedAt;
            _tmpCachedAt = _cursor.getLong(_cursorIndexOfCachedAt);
            _item = new ArticleEntity(_tmpId,_tmpTitle,_tmpDescription,_tmpContent,_tmpImageUrl,_tmpLink,_tmpAuthor,_tmpPublishedAt,_tmpCategories,_tmpIsBookmarked,_tmpCachedAt);
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
  public Flow<List<ArticleEntity>> getBookmarkedArticles() {
    final String _sql = "SELECT * FROM articles WHERE isBookmarked = 1 ORDER BY publishedAt DESC";
    final RoomSQLiteQuery _statement = RoomSQLiteQuery.acquire(_sql, 0);
    return CoroutinesRoom.createFlow(__db, false, new String[] {"articles"}, new Callable<List<ArticleEntity>>() {
      @Override
      @NonNull
      public List<ArticleEntity> call() throws Exception {
        final Cursor _cursor = DBUtil.query(__db, _statement, false, null);
        try {
          final int _cursorIndexOfId = CursorUtil.getColumnIndexOrThrow(_cursor, "id");
          final int _cursorIndexOfTitle = CursorUtil.getColumnIndexOrThrow(_cursor, "title");
          final int _cursorIndexOfDescription = CursorUtil.getColumnIndexOrThrow(_cursor, "description");
          final int _cursorIndexOfContent = CursorUtil.getColumnIndexOrThrow(_cursor, "content");
          final int _cursorIndexOfImageUrl = CursorUtil.getColumnIndexOrThrow(_cursor, "imageUrl");
          final int _cursorIndexOfLink = CursorUtil.getColumnIndexOrThrow(_cursor, "link");
          final int _cursorIndexOfAuthor = CursorUtil.getColumnIndexOrThrow(_cursor, "author");
          final int _cursorIndexOfPublishedAt = CursorUtil.getColumnIndexOrThrow(_cursor, "publishedAt");
          final int _cursorIndexOfCategories = CursorUtil.getColumnIndexOrThrow(_cursor, "categories");
          final int _cursorIndexOfIsBookmarked = CursorUtil.getColumnIndexOrThrow(_cursor, "isBookmarked");
          final int _cursorIndexOfCachedAt = CursorUtil.getColumnIndexOrThrow(_cursor, "cachedAt");
          final List<ArticleEntity> _result = new ArrayList<ArticleEntity>(_cursor.getCount());
          while (_cursor.moveToNext()) {
            final ArticleEntity _item;
            final String _tmpId;
            _tmpId = _cursor.getString(_cursorIndexOfId);
            final String _tmpTitle;
            _tmpTitle = _cursor.getString(_cursorIndexOfTitle);
            final String _tmpDescription;
            _tmpDescription = _cursor.getString(_cursorIndexOfDescription);
            final String _tmpContent;
            _tmpContent = _cursor.getString(_cursorIndexOfContent);
            final String _tmpImageUrl;
            if (_cursor.isNull(_cursorIndexOfImageUrl)) {
              _tmpImageUrl = null;
            } else {
              _tmpImageUrl = _cursor.getString(_cursorIndexOfImageUrl);
            }
            final String _tmpLink;
            _tmpLink = _cursor.getString(_cursorIndexOfLink);
            final String _tmpAuthor;
            _tmpAuthor = _cursor.getString(_cursorIndexOfAuthor);
            final long _tmpPublishedAt;
            _tmpPublishedAt = _cursor.getLong(_cursorIndexOfPublishedAt);
            final String _tmpCategories;
            _tmpCategories = _cursor.getString(_cursorIndexOfCategories);
            final boolean _tmpIsBookmarked;
            final int _tmp;
            _tmp = _cursor.getInt(_cursorIndexOfIsBookmarked);
            _tmpIsBookmarked = _tmp != 0;
            final long _tmpCachedAt;
            _tmpCachedAt = _cursor.getLong(_cursorIndexOfCachedAt);
            _item = new ArticleEntity(_tmpId,_tmpTitle,_tmpDescription,_tmpContent,_tmpImageUrl,_tmpLink,_tmpAuthor,_tmpPublishedAt,_tmpCategories,_tmpIsBookmarked,_tmpCachedAt);
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
  public Object getArticleCount(final Continuation<? super Integer> $completion) {
    final String _sql = "SELECT COUNT(*) FROM articles";
    final RoomSQLiteQuery _statement = RoomSQLiteQuery.acquire(_sql, 0);
    final CancellationSignal _cancellationSignal = DBUtil.createCancellationSignal();
    return CoroutinesRoom.execute(__db, false, _cancellationSignal, new Callable<Integer>() {
      @Override
      @NonNull
      public Integer call() throws Exception {
        final Cursor _cursor = DBUtil.query(__db, _statement, false, null);
        try {
          final Integer _result;
          if (_cursor.moveToFirst()) {
            final int _tmp;
            _tmp = _cursor.getInt(0);
            _result = _tmp;
          } else {
            _result = 0;
          }
          return _result;
        } finally {
          _cursor.close();
          _statement.release();
        }
      }
    }, $completion);
  }

  @NonNull
  public static List<Class<?>> getRequiredConverters() {
    return Collections.emptyList();
  }
}
