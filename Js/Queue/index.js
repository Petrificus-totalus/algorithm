function Queue(maxSize = 100) {
  this.maxSize = maxSize;
  this.data = [];
  this.enqueue = enqueue;
  this.dequeue = dequeue;
  this.show = show;
  this.size = size;
}
function size() {
  return this.data.length;
}

function enqueue(val) {
  if (this.size() === this.maxSize) {
    throw new Error("队列满了");
  }
  this.data.push(val);
}

function dequeue() {
  if (this.size() === 0) {
    throw new Error("队列为空");
  }
  return this.data.shift();
}

function show() {
  return this.data.join("\n");
}

/**
 * 击鼓传花游戏: 给一个队列存放人，给一个数字 num，从第一个人开始从 1 开始数数，数到 num 的人被淘汰，从这个人的下一个人重新开始从 1 开始数到 num 淘汰，直到最后剩下一个人，返回这个人
 * 思路: 在num 前循环出队第一个元素并入队，到num时出队，不入队，直到队列长度为1
 * @param {*} q
 * @param {*} num
 */
function game(q, num) {
  while (q.size() > 1) {
    let i = 1;
    while (i < num) {
      q.enqueue(q.dequeue());
      i++;
    }
    q.dequeue();
  }
  return q.data[0];
}

let queue = new Queue();
queue.enqueue(1);
queue.enqueue(2);
queue.enqueue(3);
queue.enqueue(4);
queue.enqueue(5);
queue.enqueue(6);
queue.enqueue(7);
console.log(game(queue, 5));

module.exports = {
  Q: Queue,
};
