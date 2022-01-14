import 'package:flutter/material.dart';
import 'package:game_almanac/sql_helper.dart';

void main() {
  runApp(const MyApp());
}

class MyApp extends StatelessWidget {
  const MyApp({Key key}) : super(key: key);

  // This widget is the root of your application.
  @override
  Widget build(BuildContext context) {
    return MaterialApp(
      debugShowCheckedModeBanner: false,
      title: 'Game Almanac',
      theme: ThemeData(
          primarySwatch: Colors.green,
          scaffoldBackgroundColor: const Color(0xFF81C784)),
      home: const MyHomePage(title: 'Game Almanac'),
    );
  }
}

class MyHomePage extends StatefulWidget {
  const MyHomePage({Key key, @required this.title}) : super(key: key);

  final String title;

  @override
  State<MyHomePage> createState() => _MyHomePageState();
}

class _MyHomePageState extends State<MyHomePage> {
  int _counter = 0;
  TextEditingController namaController = TextEditingController();
  TextEditingController tahunController = TextEditingController();
  TextEditingController devController = TextEditingController();

  void _incrementCounter() {
    setState(() {
      _counter++;
    });
  }

  @override
  void initState() {
    refreshGame();
    super.initState();
  }

  List<Map<String, dynamic>> game = [];
  void refreshGame() async {
    final data = await SQLHelper.getGame();
    setState(() {
      game = data;
    });
  }

  @override
  Widget build(BuildContext context) {
    print(game);
    return Scaffold(
      appBar: AppBar(
        title: Text(widget.title),
      ),
      body: ListView.builder(
          itemCount: game.length,
          itemBuilder: (context, index) => Card(
                margin: const EdgeInsets.all(15),
                child: ListTile(
                  title: Text(game[index]['nama_game']),
                  subtitle: Text(game[index]['tahun_rilis'] +
                      "\n" +
                      game[index]['developer']),
                  // trailing: Text(game[index]['tahun_rilis']),
                  isThreeLine: true,
                  trailing: SizedBox(
                    width: 100,
                    child: Row(
                      children: [
                        IconButton(
                            onPressed: () => modalForm(game[index]['id']),
                            icon: const Icon(Icons.edit)),
                        IconButton(
                            onPressed: () => hapusGame(game[index]['id']),
                            icon: const Icon(Icons.delete))
                      ],
                    ),
                  ),
                ),
              )),
      floatingActionButton: FloatingActionButton(
        onPressed: () {
          modalForm(null);
        },
        child: const Icon(Icons.add),
      ), // This trailing comma makes auto-formatting nicer for build methods.
    );
  }

  //Fungsi tambah
  Future<void> tambahGame() async {
    await SQLHelper.tambahGame(
        namaController.text, tahunController.text, devController.text);
    refreshGame();
    ScaffoldMessenger.of(context).showSnackBar(
        const SnackBar(content: Text("Data successfully added.")));
  }

  //Fungsi ubah
  Future<void> ubahGame(int id) async {
    await SQLHelper.ubahGame(
        id, namaController.text, tahunController.text, devController.text);
    refreshGame();
    ScaffoldMessenger.of(context).showSnackBar(
        const SnackBar(content: Text("Data successfully updated.")));
  }

  //Fungsi hapus
  void hapusGame(int id) async {
    await SQLHelper.hapusGame(id);
    ScaffoldMessenger.of(context).showSnackBar(
        const SnackBar(content: Text("Data successfully deleted.")));
    refreshGame();
  }

  //Form tambah
  void modalForm(int id) async {
    if (id != null) {
      final dataGame = game.firstWhere((element) => element['id'] == id);
      namaController.text = dataGame['nama_game'];
      tahunController.text = dataGame['tahun_rilis'];
      devController.text = dataGame['developer'];
    }
    showModalBottomSheet(
        context: context,
        builder: (_) => Container(
              padding: const EdgeInsets.all(15),
              width: double.infinity,
              height: 800,
              child: SingleChildScrollView(
                child: Column(
                  mainAxisSize: MainAxisSize.min,
                  crossAxisAlignment: CrossAxisAlignment.end,
                  children: [
                    TextField(
                      controller: namaController,
                      decoration: const InputDecoration(hintText: 'Nama Game'),
                    ),
                    const SizedBox(
                      height: 10,
                    ),
                    TextField(
                      controller: tahunController,
                      decoration:
                          const InputDecoration(hintText: 'Tahun Rilis'),
                    ),
                    const SizedBox(
                      height: 10,
                    ),
                    TextField(
                      controller: devController,
                      decoration:
                          const InputDecoration(hintText: 'Nama Developer'),
                    ),
                    const SizedBox(
                      height: 10,
                    ),
                    ElevatedButton(
                        onPressed: () async {
                          if (id == null) {
                            await tambahGame();
                          } else {
                            await ubahGame(id);
                          }
                          namaController.text = '';
                          tahunController.text = '';
                          devController.text = '';
                          Navigator.pop(context);
                        },
                        child: Text(id == null ? 'Tambah' : 'Update'))
                  ],
                ),
              ),
            ));
  }
}
