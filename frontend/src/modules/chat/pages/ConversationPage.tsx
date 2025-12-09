import React from 'react';
import { ConversationDto, MessageDto } from '../types';
import ConversationHeader from '../components/ConversationHeader';
import MessageList from '../components/MessageList';
import MessageInput from '../components/MessageInput';

interface Props {
  conversation: ConversationDto;
  messages: MessageDto[];
  onSend: (message: string) => void;
}

const ConversationPage: React.FC<Props> = ({ conversation, messages, onSend }) => {
  return (
    <div className="flex-1 flex flex-col">
      <ConversationHeader title={conversation.name} />
      <MessageList messages={messages} />
      <MessageInput onSend={onSend} />
    </div>
  );
};

export default ConversationPage;
