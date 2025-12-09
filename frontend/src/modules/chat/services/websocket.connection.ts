import { MessageDto } from '../types';

type MessageHandler = (msg: MessageDto) => void;

export default class WebSocketConnection {
  private ws: WebSocket;
  private userId: string;
  private handlers: MessageHandler[] = [];

  constructor(userId: string) {
    this.userId = userId;
    this.ws = new WebSocket(`${import.meta.env.VITE_WS_URL || 'ws://localhost:8080/ws'}/chat?userId=${userId}`);

    this.ws.onmessage = (event) => {
      const msg: MessageDto = JSON.parse(event.data);
      this.handlers.forEach(h => h(msg));
    };

    this.ws.onclose = () => console.log('WebSocket connection closed.');
    this.ws.onerror = (err) => console.error('WebSocket error:', err);
  }

  sendMessage(message: MessageDto) {
    this.ws.send(JSON.stringify(message));
  }

  onMessage(handler: MessageHandler) {
    this.handlers.push(handler);
  }

  close() {
    this.ws.close();
  }
}
