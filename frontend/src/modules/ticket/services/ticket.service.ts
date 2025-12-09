import axios from 'axios';
import { TicketDto } from '../types';

const API_URL = import.meta.env.VITE_API_URL || 'http://localhost:8080/api/tickets';

class TicketService {
  static async getTickets(): Promise<TicketDto[]> {
    const response = await axios.get(`${API_URL}`);
    return response.data;
  }

  static async getTicket(ticketId: number): Promise<TicketDto> {
    const response = await axios.get(`${API_URL}/${ticketId}`);
    return response.data;
  }

  static async assignTicket(ticketId: number, userId: string): Promise<void> {
    await axios.post(`${API_URL}/${ticketId}/assign`, { userId });
  }

  static async createTicket(data: Partial<TicketDto>): Promise<TicketDto> {
    const response = await axios.post(`${API_URL}`, data);
    return response.data;
  }
}

export default TicketService;
