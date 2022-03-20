function Graph() {
  this.vertex = []; // 顶点
  this.edges = {}; // 边
  this.addVertex = addVertex;
  this.addEdge = addEdge;
  this.show = show;
  this.bfs = bfs;
  this.BFS_shortestPath = BFS_shortestPath;
  this.dfs = dfs;
}

function addVertex(v) {
  this.vertex.push(v);
  this.edges[v] = [];
}

function addEdge(v1, v2) {
  this.edges[v1].push(v2);
  this.edges[v2].push(v1);
}

function show() {
  let str = "";
  for (let item of this.vertex) {
    str += `${item}=>`;
    for (let item1 of this.edges[item]) {
      str += `${item1}`;
    }
    str += "\n";
  }
  console.log(str);
}

/**
 * BFS广度优先搜索，用队列实现，与层序遍历相似
 * 一个节点: 白色--未访问   灰色--正在访问(已加入队列)  黑色--访问完成
 * 将图的一个节点先入队
 * 1. 出队一个节点 cur，将 cur 改为黑色。
 * 2. 遍历 cur 的所有相连节点 N，若是白色，则将 N 入队，并将 N 改为灰色
 *
 * PS: 为什么要灰色？
 * A---B
 * |_C_|
 * 对于环状的假设目前 cur 为 A，那么遍历他的两个边将BC都入队，
 * 若不将 BC 改为 grey，接下来到 B 出队作为 cur，遍历他的边发现 C 还是白色那么 C 就会重复入队
 */
function initColor() {
  let color = {};
  for (let item of this.vertex) {
    color[item] = "white";
  }
  return color;
}
function bfs(v) {
  let color = initColor.call(this);
  let q = new Queue();
  q.enqueue(v);
  while (q.size() !== 0) {
    let cur = q.dequeue();
    console.log(cur);
    color[cur] = "black";

    let edge = this.edges[cur];
    for (let item of edge) {
      if (color[item] === "white") {
        color[item] = "grey";
        q.enqueue(item);
      }
    }
  }
}

function BFS_shortestPath(v) {
  let d = {}; // { A: 0, B: 1, C: 1, D: 1, E: 2, F: 2 } 记录每个节点和v的距离，这里v就是A
  let preV = {}; // { A: null, B: 'A', C: 'A', D: 'A', E: 'B', F: 'B' } 记录每个节点的上一个节点
  // 例如 C 的上一个是 A, 那么 d[C] = d[A] + AC间距。这里间距默认记成1
  for (let item of this.vertex) {
    // 初始化
    d[item] = 0;
    preV[item] = null;
  }
  let color = initColor.call(this);
  let q = new Queue();
  q.enqueue(v);
  while (q.size() !== 0) {
    let cur = q.dequeue();
    color[cur] = "black";
    let edge = this.edges[cur];
    for (let item of edge) {
      if (color[item] === "white") {
        // 和 bfs 比较就加了这两句
        d[item] = d[cur] + 1;
        preV[item] = cur;
        color[item] = "grey";
        q.enqueue(item);
      }
    }
  }
  return { d, preV };
}

function dfs(v) {
  let color = initColor.call(this);

  let edges = this.edges;
  h(color, v, edges);
}

function h(color, v, edges) {
  color[v] = "grey";
  for (let item of edges[v]) {
    if (color[item] === "white") {
      h(color, item, edges);
    }
  }
  color[v] = "black";
  console.log(v);
}

let g = new Graph();
g.addVertex("A");
g.addVertex("B");
g.addVertex("C");
g.addVertex("D");
g.addVertex("E");
g.addVertex("F");
g.addEdge("A", "B");
g.addEdge("A", "C");
g.addEdge("A", "D");
g.addEdge("B", "E");
g.addEdge("B", "F");
g.addEdge("C", "D");
g.addEdge("D", "F");

// g.bfs("A"); // 从A开始广度优先遍历
g.dfs("A");
console.log(g.BFS_shortestPath("A"));

// 从 from 到 to 的最短距离
function shortest(from, to) {
  let res = g.BFS_shortestPath(from);
  let v = to;
  while (v !== from) {
    // 从to开始循环往回找他的前一个节点直到from
    console.log(v);
    v = res.preV[v];
  }
  console.log(v);
}

// shortest("A", "F");
// g.show();

function Queue() {
  this.data = [];
  this.enqueue = (val) => this.data.push(val);
  this.dequeue = () => this.data.shift();
  this.size = () => this.data.length;
}
