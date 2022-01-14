import 'dart:async';

import 'package:sqflite/sqflite.dart' as sql;

class SQLHelper {
  //Fungsi membuat database
  static Future<void> createTables(sql.Database database) async {
    await database.execute("""
    CREATE TABLE game(
      id INTEGER PRIMARY KEY AUTOINCREMENT NOT NULL,
      nama_game TEXT NOT NULL,
      tahun_rilis TEXT NOT NULL,
      developer TEXT NOT NULL
    )
    """);
  }

  static Future<sql.Database> db() async {
    return sql.openDatabase('game.db', version: 1,
        onCreate: (sql.Database database, int version) async {
      await createTables(database);
    });
  }

  //Tambah database
  static Future<int> tambahGame(
      String nama_game, String tahun_rilis, String developer) async {
    final db = await SQLHelper.db();
    final data = {
      'nama_game': nama_game,
      'tahun_rilis': tahun_rilis,
      'developer': developer
    };
    return await db.insert('game', data);
  }

  //Ambil data
  static Future<List<Map<String, dynamic>>> getGame() async {
    final db = await SQLHelper.db();
    return db.query('game');
  }

  //Fungsi ubah data
  static Future<int> ubahGame(
      int id, String nama_game, String tahun_rilis, String developer) async {
    final db = await SQLHelper.db();

    final data = {
      'nama_game': nama_game,
      'tahun_rilis': tahun_rilis,
      'developer': developer
    };

    return await db.update('game', data, where: "id = $id");
  }

  //Fungsi hapus
  static Future<int> hapusGame(int id) async {
    final db = await SQLHelper.db();
    await db.delete('game', where: 'id = $id');
  }
}
