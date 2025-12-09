import axios from 'axios';
import { ConversationDto, MessageDto } from '../types';

const API_URL = import.meta.env.VITE_API_URL || 'http://localhost:8080/api/chat';

class ChatService {
  static async getConversations(userId: string): Promise<ConversationDto[]> {
    const response = await axios.get(`${API_URL}/conversations/${userId}`);
    return response.data;
  }

  static async getConversation(conversationId: string): Promise<ConversationDto> {
    const response = await axios.get(`${API_URL}/conversations/detail/${conversationId}`);
    return response.data;
  }

  static async getMessages(conversationId: string): Promise<MessageDto[]> {
    const response = await axios.get(`${API_URL}/messages/${conversationId}`);
    return response.data;
  }

  static async sendMessage(message: Partial<MessageDto>): Promise<MessageDto> {
    const response = await axios.post(`${API_URL}/messages`, message);
    return response.data;
  }
}

export default ChatService;
