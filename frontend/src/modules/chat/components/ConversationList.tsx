import React from 'react';
import { ConversationDto } from '../types';

interface Props {
  conversations: ConversationDto[];
  onSelect: (id: string) => void;
  selectedId?: string;
}

const ConversationList: React.FC<Props> = ({ conversations, onSelect, selectedId }) => {
  return (
    <ul>
      {conversations.map(conv => (
        <li
          key={conv.id}
          onClick={() => onSelect(conv.id)}
          className={`p-3 cursor-pointer hover:bg-gray-200 ${selectedId === conv.id ? 'bg-gray-300' : ''}`}
        >
          {conv.name}
        </li>
      ))}
    </ul>
  );
};

export default ConversationList;
